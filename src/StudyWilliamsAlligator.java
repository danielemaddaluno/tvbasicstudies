/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;


//NOTE: This study has a hardcoded custom properties on the client side (defaults.js)
@StudyDecl(studyId = "WilliamsAlligator", isPriceStudy = true,
           title = "Williams Alligator", shortTitle = "Alligator")
public class StudyWilliamsAlligator extends Study
{
    @StudyArgSourceLength(name = "jaw length", defval=21)
    int jawLength;

    @StudyArgSourceLength(name = "teeth length", defval=13)
    int teethLength;

    @StudyArgSourceLength(name = "lips length", defval=8)
    int lipsLength;


    @StudyPlot(name = "Jaw")
    @StudyPlotStyle(title = "Jaw", color = "#131384")
    Series jaw;

    @StudyPlot(name = "Teeth")
    @StudyPlotStyle(title = "Teeth", color = "#a72323")
    Series teeth;

    @StudyPlot(name = "Lips")
    @StudyPlotStyle(title = "Lips", color = "#138413")
    Series lips;


    @Override
    public void init()
    {
        Series hl2 = avg(high(), low());
        jaw = ema(hl2, jawLength);
        teeth = ema(hl2, teethLength);
        lips = ema(hl2, lipsLength);
    }
}
