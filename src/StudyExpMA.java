/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId="MAExp", isPriceStudy=true,
           title ="Moving Average Exponentional",
           shortTitle ="EMA")
@StudyOffset(min=-500, val=0, max=500)
public class StudyExpMA extends Study
{
    @StudyArgSourceLength(name = "length", defval = 9)
    int length;

    @StudyArgSource(name = "source", defval= BarSet.Source.CLOSE)
    Series source;

    @StudyPlot(name="MovAvgExp")
    @StudyPlotStyle(title="MA", visible=true, color = "#3A78AA")
    Series out;

    @Override
    public void init()
    {
        out = ema(source, length);
    }
}
