from PIL import Image, ImageDraw, ImageFont
import os

def text_to_png(input_file, output_file, font_size=14, padding=40):
    """Convert a text file to a PNG image."""

    # Read the text file
    with open(input_file, 'r', encoding='utf-8') as f:
        text = f.read()

    lines = text.split('\n')

    # Try to use a monospace font
    try:
        # Try Consolas (Windows)
        font = ImageFont.truetype("consola.ttf", font_size)
    except:
        try:
            # Try Courier New
            font = ImageFont.truetype("cour.ttf", font_size)
        except:
            # Fall back to default
            font = ImageFont.load_default()

    # Calculate image dimensions
    # Create a temporary image to measure text
    temp_img = Image.new('RGB', (1, 1))
    temp_draw = ImageDraw.Draw(temp_img)

    # Find the widest line and total height
    max_width = 0
    line_height = font_size + 4

    for line in lines:
        bbox = temp_draw.textbbox((0, 0), line, font=font)
        width = bbox[2] - bbox[0]
        if width > max_width:
            max_width = width

    # Calculate final dimensions
    img_width = max_width + (padding * 2)
    img_height = (len(lines) * line_height) + (padding * 2)

    # Create the image with white background
    img = Image.new('RGB', (img_width, img_height), color='white')
    draw = ImageDraw.Draw(img)

    # Draw the text
    y = padding
    for line in lines:
        draw.text((padding, y), line, font=font, fill='black')
        y += line_height

    # Save as PNG
    img.save(output_file, 'PNG')
    print(f"Created: {output_file}")

# Set the directory
base_dir = r"c:\Users\jamer\Desktop\CMSC 345 Software Eng Principles and Techniques\Assignment-Resources-W4"

# Generate PNGs
text_to_png(
    os.path.join(base_dir, "architecture-diagram.txt"),
    os.path.join(base_dir, "architecture-diagram.png"),
    font_size=12
)

text_to_png(
    os.path.join(base_dir, "uml-class-diagram.txt"),
    os.path.join(base_dir, "uml-class-diagram.png"),
    font_size=11
)

print("\nDone! PNG files created.")
