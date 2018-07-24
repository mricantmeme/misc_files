package com.dicv.truck.report;

import java.awt.Color;

import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Stretching;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;

public class ReportLayout {

	public static Style createHeaderStyle() {
		StyleBuilder sb = new StyleBuilder(true);
		sb.setFont(Font.VERDANA_MEDIUM_BOLD);
		sb.setBorder(Border.THIN());
		sb.setBorderBottom(Border.THIN());
		sb.setBorderColor(Color.BLACK);
		sb.setBackgroundColor(Color.LIGHT_GRAY);
		sb.setTextColor(Color.BLACK);
		sb.setHorizontalAlign(HorizontalAlign.CENTER);
		sb.setVerticalAlign(VerticalAlign.MIDDLE);
		sb.setTransparency(Transparency.OPAQUE);
		sb.setStretching(Stretching.RELATIVE_TO_TALLEST_OBJECT);
		sb.setPadding(5);
		return sb.build();
	}

	public static Style createDetailTextStyle() {
		StyleBuilder sb = new StyleBuilder(true);
		sb.setFont(Font.VERDANA_MEDIUM);
		sb.setBorder(Border.THIN());
		sb.setBorderColor(Color.BLACK);
		sb.setTextColor(Color.BLACK);
		sb.setHorizontalAlign(HorizontalAlign.CENTER);
		sb.setVerticalAlign(VerticalAlign.MIDDLE);
		sb.setPaddingLeft(5);
		sb.setStretching(Stretching.RELATIVE_TO_TALLEST_OBJECT);
		sb.setStretchWithOverflow(true);
		return sb.build();
	}

	public static Style createDetailNumberStyle() {
		StyleBuilder sb = new StyleBuilder(true);
		sb.setFont(Font.VERDANA_MEDIUM);
		sb.setBorder(Border.THIN());
		sb.setBorderColor(Color.BLACK);
		sb.setTextColor(Color.BLACK);
		sb.setHorizontalAlign(HorizontalAlign.RIGHT);
		sb.setVerticalAlign(VerticalAlign.MIDDLE);
		sb.setStretching(Stretching.RELATIVE_TO_TALLEST_OBJECT);
		sb.setPaddingRight(5);
		sb.setPattern("#,##0.00");
		return sb.build();
	}

	public static Style createLinkStyle() {
		Style linkStyle = new Style();
		linkStyle.setFont(new Font(Font.MEDIUM, Font.VERDANA_MEDIUM.getFontName(), false, false, true));
		linkStyle.setBorder(Border.THIN());
		linkStyle.setTextColor(Color.BLUE);
		linkStyle.setStretchWithOverflow(true);
		linkStyle.setBackgroundColor(Color.WHITE);
		linkStyle.setOverridesExistingStyle(true);
		linkStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		linkStyle.setVerticalAlign(VerticalAlign.MIDDLE);
		linkStyle.setPaddingRight(5);
		return linkStyle;
	}

}
