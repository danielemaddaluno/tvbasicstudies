/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "Volume", title = "Volume", shortTitle = "Vol", transparency = 87)
@StudyPalette(name = "volumePalette",
              colors = {@StudyColor(color = "#007F0E", title = "Growing"),
                        @StudyColor(color = "#872323", title = "Falling")})
public class StudyVolume extends Study
{
    @StudyArgSourceLength(name = "length", defval = 20, title = "MA Length")
    int length;

    @StudyPlot(name = "vol", palettePlotName = "vol_color", paletteName = "volumePalette")
    @StudyPlotStyle(title = "Volume", plotStyle = CanvasEx.PlotStyle.COLUMNS)
    Series outVolume;

    @StudyPlot(name = "vol_ma")
    @StudyPlotStyle(title="Volume MA", plotStyle= CanvasEx.PlotStyle.AREA,
                    color="#0496FF", transparency=70)
    Series outMA;

    @Override
    public void init()
    {
        outVolume = volume();
        setPalette(outVolume, gt(open(), close()));
        outMA = sma(volume(), length);
    }
}
