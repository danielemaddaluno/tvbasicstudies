/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;
import studylib.sdk.studies.Regression;


//NOTE: This study has a hardcoded custom properties on the client side (defaults.js)
@StudyDecl(studyId = "LinearRegression", isPriceStudy = true, title = "Linear Regression",
           shortTitle = "Lin Reg")
public class StudyLinearRegression extends Study
{
    @StudyArgInt(name = "upper diviation", defval = 2, minval = -500, maxval = 500, title = "Upper Deviation")
    int upperDev;

    @StudyArgInt(name = "lower diviation", defval = -2, minval = -500, maxval = 500, title = "Lower Deviation")
    int lowerDev;

    @StudyArgBool(name = "use upper diviation", defval = true, title = "Use Upper Deviation")
    boolean useUpperDev;

    @StudyArgBool(name = "use lower diviation", defval = true, title = "Use Lower Deviation")
    boolean useLowerDev;

    @StudyArgSourceLength(name = "count", defval = 100, title = "Count")
    int count;

    @StudyArgSource(name = "source", defval = BarSet.Source.CLOSE, title = "Source")
    Series source;

    @StudyOutputData
    JsonObj.Regression data = new JsonObj.Regression();

    @Override
    public void init()
    {
        newSeries(new Regression.Linear(upperDev, lowerDev, useUpperDev, useLowerDev,
                count, source, barSet(), data));
    }
}
