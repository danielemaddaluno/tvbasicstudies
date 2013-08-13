/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "MAWeighted", isPriceStudy = true,
           title = "Moving Average Weighted", shortTitle = "WMA")
@StudyOffset(min=-500, val=0, max=500)
public class StudyMAWeighted extends Study
{
    @StudyArgSourceLength(name = "length", defval=9)
    int length;

    @StudyArgSource(name = "source", defval=BarSet.Source.CLOSE)
    Series source;

    @StudyPlot(name = "MovAvgWeighted")
    @StudyPlotStyle(title="MA", color = "#3A61A5")
    Series out;

    @Override
    public void init()
    {
        out = wma(source, length);
    }
}
