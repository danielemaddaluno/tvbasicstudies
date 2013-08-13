/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "AwesomeOscillator", title = "Awesome Oscillator", shortTitle = "AO")
@StudyPalette(
        name = "pal",
        colors = {
            @StudyColor(color = "#007F0E", title = "Growing"),
            @StudyColor(color = "#872323", title = "Falling")})
public class StudyAwesomeOscillator extends Study
{
    @StudyPlot(name = "ao", paletteName = "pal")
    @StudyPlotStyle(title="OA", plotStyle=CanvasEx.PlotStyle.HISTOGRAM, color="#ff006e")
    Series out;

    @Override
    public void init()
    {
        Series src = hl2();
        out = diff(sma(src, 5), sma(src, 34));
        setPalette(out, le(out, offset(out, 1)));
    }
}
