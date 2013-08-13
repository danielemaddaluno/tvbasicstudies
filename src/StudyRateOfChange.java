/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "ROC", title = "Rate Of Change", shortTitle = "ROC")
public class StudyRateOfChange extends Study
{
    @StudyArgSourceLength(name = "length", defval=9)
    int length;

    @StudyArgSource(name = "source", defval=BarSet.Source.CLOSE)
    Series source;

    @StudyPlot(name = "ROC")
    @StudyPlotStyle(title = "ROC", color = "#156a99")
    Series out;

    @Override
    public void init()
    {
        out = roc(source, length);
    }
}
