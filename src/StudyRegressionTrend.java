/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.core.BarSetDataRange;
import studylib.sdk.*;
import studylib.sdk.studies.Regression;

@StudyDecl(studyId = "RegressionTrend", isPriceStudy = true, title = "Regression Trend",
           shortTitle = "Reg Trend")
public class StudyRegressionTrend extends Study
{
    @StudyArgInt(name = "upper diviation", defval = 2, minval = -500, maxval = 500, title="Upper Deviation")
    int upperDev;

    @StudyArgInt(name = "lower diviation", defval = -2, minval = -500, maxval = 500, title="Lower Deviation")
    int lowerDev;

    @StudyArgBool(name = "use upper diviation", defval = true, title="Use Upper Deviation")
    boolean useUpperDev;

    @StudyArgBool(name = "use lower diviation", defval = true, title="Use Lower Deviation")
    boolean useLowerDev;

    @StudyArgTime(name = "first bar time")
    long firstBarTime;

    @StudyArgTime(name = "last bar time")
    long lastBarTime;

    @StudyArgSource(name="source", defval=BarSet.Source.CLOSE, title="Source")
    Series source;

    @StudyOutputData
    JsonObj.Regression data = new JsonObj.Regression();

    @Override
    public void init()
    {
        barSet().setDataRange(new BarSetDataRange(firstBarTime, lastBarTime));
        newSeries(new Regression.TrendOld(upperDev, lowerDev, useUpperDev, useLowerDev,
                firstBarTime, lastBarTime, source, barSet(), data));
    }
}
