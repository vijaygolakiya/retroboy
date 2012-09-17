package se.embargo.retroboy.color;

public class YuvPalette implements IPalette {
	private final int[] _palette;
	
	public YuvPalette(int[] palette) {
		_palette = palette;
	}
	
	public int getPaletteSize() {
		return _palette.length;
	}
	
	public int getNearestColor(final int r1, final int g1, final int b1) {
		int color = 0;
		int mindistance = Integer.MAX_VALUE;
		
		for (int i = 0; i < _palette.length; i++) {
			final int r2 = (_palette[i] & 0x000000ff),
					  g2 = ((_palette[i] & 0x0000ff00) >> 8),
					  b2 = ((_palette[i] & 0x00ff0000) >> 16);

			final int dr = r1 - r2,
				  	  dg = g1 - g2,
				  	  db = b1 - b2;
		
			final int distance = 3 * dr * dr + 4 * dg * dg + 2 * db * db;
			if (distance < mindistance) {
				color = _palette[i];
				mindistance = distance;
			}
		}
		
		return color;
	}
	
	public int getNearestColor(final int pixel) {
		return getNearestColor((pixel & 0x000000ff), (pixel & 0x0000ff00) >> 8, (pixel & 0x00ff0000) >> 16);
	}
}
