from PIL import Image, ImageDraw, ImageFont
import os

# Read the Java file
with open('Week4SimpleGUI.java', 'r') as f:
    code_lines = f.readlines()

# Settings
font_size = 14
line_height = 18
padding = 20
bg_color = (40, 44, 52)  # Dark background
text_color = (171, 178, 191)  # Light gray text
line_number_color = (92, 99, 112)  # Darker gray for line numbers

# Try to use a monospace font, fallback to default
try:
    font = ImageFont.truetype("consola.ttf", font_size)
except:
    try:
        font = ImageFont.truetype("C:\\Windows\\Fonts\\consola.ttf", font_size)
    except:
        font = ImageFont.load_default()

# Calculate image dimensions
max_line_length = max(len(line.rstrip()) for line in code_lines)
line_number_width = len(str(len(code_lines))) + 1
total_chars = line_number_width + 2 + max_line_length

img_width = padding * 2 + total_chars * (font_size // 2 + 3)
img_height = padding * 2 + len(code_lines) * line_height

# Create image
img = Image.new('RGB', (img_width, img_height), bg_color)
draw = ImageDraw.Draw(img)

# Draw code with line numbers
y = padding
for i, line in enumerate(code_lines, 1):
    line_num = str(i).rjust(line_number_width)
    line_text = line.rstrip()

    # Draw line number
    draw.text((padding, y), line_num, fill=line_number_color, font=font)

    # Draw code line
    x_offset = padding + (line_number_width + 2) * (font_size // 2 + 3)
    draw.text((x_offset, y), line_text, fill=text_color, font=font)

    y += line_height

# Save image
output_path = 'Week4SimpleGUI_code.png'
img.save(output_path)
print(f"Code image saved to: {output_path}")
