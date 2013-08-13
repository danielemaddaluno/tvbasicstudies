/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId="MASimple", isPriceStudy=true,
           title ="Moving Average", shortTitle ="MA")
@StudyOffset(min=-500, val=0, max=500)
public class StudySimpleMA extends Study
{
    @StudyArgSourceLength(name="length", defval=9)
    int length;

    @StudyArgSource(name="source", defval=BarSet.Source.CLOSE)
    Series source;

    @StudyPlot(name="MovAvgSimple")
    @StudyPlotStyle(title="MA", color="#3A85AD",
                    lineStyle= CanvasEx.LineStyle.SOLID, plotStyle= CanvasEx.PlotStyle.LINE)
    Series out;

    @Override
    public void init()
    {
        out = sma(source, length);
    }
}
