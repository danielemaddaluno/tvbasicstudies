/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl()
public class StudyRegressionCurve extends Study
{
    @StudyArgSource(name = "source", defval = BarSet.Source.CLOSE)
    Series source;

    @StudyArgInt(name = "length", defval = 10, minval = 2, maxval = 500)
    int length;

    @StudyArgInt(name = "offset", defval = 0, minval = -100, maxval = 100)
    int offset;

    @StudyPlot(name = "regression")
    Series out;

    @Override
    public void init()
    {
        out = linreg(source, length, offset);
    }

}
