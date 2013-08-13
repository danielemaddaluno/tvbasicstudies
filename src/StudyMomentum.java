/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "MOM", title = "Momentum", shortTitle = "Mom")
public class StudyMomentum extends Study
{
    @StudyArgSourceLength(name = "length", defval=10)
    int length;

    @StudyArgSource(name = "source", defval = BarSet.Source.CLOSE)
    Series source;

    @StudyPlot(name = "Momentum")
    @StudyPlotStyle(title = "MOM", color = "#999915")
    Series out;

    @Override
    public void init()
    {
        out = change(source, length);
    }
}
