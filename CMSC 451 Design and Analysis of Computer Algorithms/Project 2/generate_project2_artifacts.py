from __future__ import annotations

import csv
import math
from decimal import Decimal, ROUND_HALF_UP
from pathlib import Path


RUNS_PER_SIZE = 40
PROJECT_DIR = Path(__file__).resolve().parent
QUICK_INPUT = PROJECT_DIR / "QuickSort.txt"
MERGE_INPUT = PROJECT_DIR / "MergeSort.txt"
SUMMARY_OUTPUT = PROJECT_DIR / "Project 2 Summary.csv"
GRAPH_OUTPUT = PROJECT_DIR / "Project 2 Graphs.svg"


def main() -> None:
    quick_rows = parse_results(QUICK_INPUT)
    merge_rows = parse_results(MERGE_INPUT)

    if len(quick_rows) != len(merge_rows):
        raise ValueError("Quick Sort and Merge Sort files do not contain the same number of sizes.")

    summary_rows = build_summary_rows(quick_rows, merge_rows)
    write_summary_csv(summary_rows, SUMMARY_OUTPUT)
    write_svg_graphs(summary_rows, GRAPH_OUTPUT)

    print(f"Wrote {SUMMARY_OUTPUT.name}")
    print(f"Wrote {GRAPH_OUTPUT.name}")


def parse_results(path: Path) -> list[dict[str, object]]:
    rows: list[dict[str, object]] = []

    for line_number, raw_line in enumerate(path.read_text(encoding="utf-8").splitlines(), start=1):
        line = raw_line.strip()
        if not line:
            continue

        parts = line.split()
        size = int(parts[0])
        pairs = parts[1:]

        if len(pairs) != RUNS_PER_SIZE * 2:
            raise ValueError(
                f"{path.name}:{line_number} expected {RUNS_PER_SIZE} count/time pairs, found {len(pairs) // 2}."
            )

        counts = [float(pairs[index]) for index in range(0, len(pairs), 2)]
        times = [float(pairs[index]) for index in range(1, len(pairs), 2)]

        rows.append(
            {
                "size": size,
                "counts": counts,
                "times": times,
            }
        )

    return rows


def build_summary_rows(
    quick_rows: list[dict[str, object]], merge_rows: list[dict[str, object]]
) -> list[dict[str, float]]:
    summary_rows: list[dict[str, float]] = []

    for quick_row, merge_row in zip(quick_rows, merge_rows):
        quick_size = int(quick_row["size"])
        merge_size = int(merge_row["size"])
        if quick_size != merge_size:
            raise ValueError(f"Size mismatch: Quick Sort has {quick_size}, Merge Sort has {merge_size}.")

        quick_counts = list(quick_row["counts"])
        quick_times = list(quick_row["times"])
        merge_counts = list(merge_row["counts"])
        merge_times = list(merge_row["times"])

        quick_avg_count = average(quick_counts)
        quick_avg_time = average(quick_times)
        merge_avg_count = average(merge_counts)
        merge_avg_time = average(merge_times)

        summary_rows.append(
            {
                "Size": quick_size,
                "Quick Avg Count": quick_avg_count,
                "Quick Avg Count Display": average_half_up_string(quick_counts),
                "Quick Count CV %": coefficient_of_variation(quick_counts, quick_avg_count),
                "Quick Avg Time ns": quick_avg_time,
                "Quick Avg Time ns Display": average_half_up_string(quick_times),
                "Quick Time CV %": coefficient_of_variation(quick_times, quick_avg_time),
                "Merge Avg Count": merge_avg_count,
                "Merge Avg Count Display": average_half_up_string(merge_counts),
                "Merge Count CV %": coefficient_of_variation(merge_counts, merge_avg_count),
                "Merge Avg Time ns": merge_avg_time,
                "Merge Avg Time ns Display": average_half_up_string(merge_times),
                "Merge Time CV %": coefficient_of_variation(merge_times, merge_avg_time),
            }
        )

    return summary_rows


def average(values: list[float]) -> float:
    return sum(values) / len(values)


def coefficient_of_variation(values: list[float], mean: float) -> float:
    if mean == 0.0:
        return 0.0

    variance = sum((value - mean) ** 2 for value in values) / len(values)
    return math.sqrt(variance) / mean * 100.0


def write_summary_csv(summary_rows: list[dict[str, float]], output_path: Path) -> None:
    fieldnames = [
        "Size",
        "Quick Avg Count",
        "Quick Count CV %",
        "Quick Avg Time ns",
        "Quick Time CV %",
        "Merge Avg Count",
        "Merge Count CV %",
        "Merge Avg Time ns",
        "Merge Time CV %",
    ]

    with output_path.open("w", newline="", encoding="utf-8") as csv_file:
        writer = csv.DictWriter(csv_file, fieldnames=fieldnames)
        writer.writeheader()
        for row in summary_rows:
            writer.writerow(
                {
                    "Size": int(row["Size"]),
                    "Quick Avg Count": row["Quick Avg Count Display"],
                    "Quick Count CV %": format_decimal(row["Quick Count CV %"]),
                    "Quick Avg Time ns": row["Quick Avg Time ns Display"],
                    "Quick Time CV %": format_decimal(row["Quick Time CV %"]),
                    "Merge Avg Count": row["Merge Avg Count Display"],
                    "Merge Count CV %": format_decimal(row["Merge Count CV %"]),
                    "Merge Avg Time ns": row["Merge Avg Time ns Display"],
                    "Merge Time CV %": format_decimal(row["Merge Time CV %"]),
                }
            )


def format_decimal(value: float) -> str:
    return str(Decimal(str(value)).quantize(Decimal("0.01"), rounding=ROUND_HALF_UP))


def average_half_up_string(values: list[float]) -> str:
    total = sum(Decimal(int(value)) for value in values)
    average_value = total / Decimal(len(values))
    return str(average_value.quantize(Decimal("0.01"), rounding=ROUND_HALF_UP))


def write_svg_graphs(summary_rows: list[dict[str, float]], output_path: Path) -> None:
    width = 980
    height = 860

    comparison_box = GraphBox(
        left=90,
        top=110,
        width=780,
        height=250,
        title="Average Critical Operation Count",
        y_axis_label="Average comparisons",
        max_value=20000.0,
        tick_step=5000.0,
    )
    time_box = GraphBox(
        left=90,
        top=500,
        width=780,
        height=250,
        title="Average Execution Time",
        y_axis_label="Average time (ns)",
        max_value=400000.0,
        tick_step=100000.0,
    )

    lines = [
        f'<svg xmlns="http://www.w3.org/2000/svg" width="{width}" height="{height}" viewBox="0 0 {width} {height}">',
        '<style>text { font-family: Georgia, "Times New Roman", serif; }</style>',
        '<rect width="100%" height="100%" fill="white" />',
        '<text x="40" y="40" font-size="26" font-weight="700" fill="#222">Project 2 Benchmark Graphs</text>',
        '<text x="40" y="68" font-size="14" fill="#555">Quick Sort vs Merge Sort using the Project 1 benchmark outputs</text>',
        '<rect x="700" y="30" width="220" height="54" rx="8" fill="#f8f8f8" stroke="#ccc" />',
        '<line x1="720" y1="50" x2="760" y2="50" stroke="#1f77b4" stroke-width="3" />',
        '<text x="770" y="54" font-size="13" fill="#333">Quick Sort</text>',
        '<line x1="720" y1="70" x2="760" y2="70" stroke="#d62728" stroke-width="3" />',
        '<text x="770" y="74" font-size="13" fill="#333">Merge Sort</text>',
    ]

    lines.extend(draw_graph(summary_rows, comparison_box, "Quick Avg Count", "Merge Avg Count"))
    lines.extend(draw_graph(summary_rows, time_box, "Quick Avg Time ns", "Merge Avg Time ns"))
    lines.append("</svg>")

    output_path.write_text("\n".join(lines) + "\n", encoding="utf-8")


def draw_graph(summary_rows: list[dict[str, float]], box: "GraphBox", quick_key: str, merge_key: str) -> list[str]:
    sizes = [int(row["Size"]) for row in summary_rows]
    quick_values = [float(row[quick_key]) for row in summary_rows]
    merge_values = [float(row[merge_key]) for row in summary_rows]

    lines = [
        f'<text x="40" y="{box.top - 15}" font-size="18" font-weight="700" fill="#222">{box.title}</text>',
        f'<line x1="{box.left}" y1="{box.top}" x2="{box.left}" y2="{box.bottom}" stroke="#333" stroke-width="1.5" />',
        f'<line x1="{box.left}" y1="{box.bottom}" x2="{box.right}" y2="{box.bottom}" stroke="#333" stroke-width="1.5" />',
    ]

    for index, size in enumerate(sizes):
        x = scale_x(index, len(sizes), box.left, box.right)
        lines.append(f'<line x1="{x:.1f}" y1="{box.bottom}" x2="{x:.1f}" y2="{box.bottom + 6}" stroke="#333" />')
        lines.append(
            f'<text x="{x:.1f}" y="{box.bottom + 24}" font-size="12" text-anchor="middle" fill="#333">{size}</text>'
        )

    tick_count = int(box.max_value / box.tick_step) + 1
    for tick_index in range(tick_count):
        value = tick_index * box.tick_step
        y = scale_y(value, box.top, box.bottom, box.max_value)
        lines.append(f'<line x1="{box.left - 6}" y1="{y:.1f}" x2="{box.right}" y2="{y:.1f}" stroke="#ddd" />')
        lines.append(
            f'<text x="{box.left - 10}" y="{y + 4:.1f}" font-size="12" text-anchor="end" fill="#333">{value:,.0f}</text>'
        )

    lines.append(
        f'<polyline fill="none" stroke="#1f77b4" stroke-width="2.5" points="{build_polyline_points(quick_values, box)}" />'
    )
    lines.extend(build_point_circles(quick_values, box, "#1f77b4"))

    lines.append(
        f'<polyline fill="none" stroke="#d62728" stroke-width="2.5" points="{build_polyline_points(merge_values, box)}" />'
    )
    lines.extend(build_point_circles(merge_values, box, "#d62728"))

    x_label_y = box.bottom + 35
    y_label_y = box.top + box.height / 2
    lines.append(
        f'<text x="{(box.left + box.right) / 2:.0f}" y="{x_label_y}" font-size="13" text-anchor="middle" fill="#333">Input size n</text>'
    )
    lines.append(
        f'<text x="25" y="{y_label_y:.0f}" font-size="13" text-anchor="middle" transform="rotate(-90 25,{y_label_y:.0f})" fill="#333">{box.y_axis_label}</text>'
    )

    return lines


def build_polyline_points(values: list[float], box: "GraphBox") -> str:
    points = []
    for index, value in enumerate(values):
        x = scale_x(index, len(values), box.left, box.right)
        y = scale_y(value, box.top, box.bottom, box.max_value)
        points.append(f"{x:.1f},{y:.1f}")
    return " ".join(points)


def build_point_circles(values: list[float], box: "GraphBox", color: str) -> list[str]:
    circles = []
    for index, value in enumerate(values):
        x = scale_x(index, len(values), box.left, box.right)
        y = scale_y(value, box.top, box.bottom, box.max_value)
        circles.append(f'<circle cx="{x:.1f}" cy="{y:.1f}" r="3" fill="{color}" />')
    return circles


def scale_x(index: int, count: int, left: float, right: float) -> float:
    if count == 1:
        return left
    return left + (right - left) * index / (count - 1)


def scale_y(value: float, top: float, bottom: float, max_value: float) -> float:
    return bottom - (value / max_value) * (bottom - top)


class GraphBox:
    def __init__(
        self,
        *,
        left: int,
        top: int,
        width: int,
        height: int,
        title: str,
        y_axis_label: str,
        max_value: float,
        tick_step: float,
    ) -> None:
        self.left = left
        self.top = top
        self.width = width
        self.height = height
        self.right = left + width
        self.bottom = top + height
        self.title = title
        self.y_axis_label = y_axis_label
        self.max_value = max_value
        self.tick_step = tick_step


if __name__ == "__main__":
    main()
