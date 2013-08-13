/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "MAVolumeWeighted", isPriceStudy = true,
           title = "VWMA", shortTitle = "VWMA")
@StudyOffset(min=-500, val=0, max=500)
public class StudyVWMA extends Study
{
    @StudyArgSourceLength(name = "length", defval=20)
    int length;

    @StudyArgSource(name = "source", defval=BarSet.Source.CLOSE)
    Series source;

    @StudyPlot(name = "MovAvgVolumeWeighted")
    @StudyPlotStyle(title = "VWMA", color = "#3A6CA8")
    Series out;

    @Override
    public void init()
    {
        out = vwma(source, length);
    }
}
