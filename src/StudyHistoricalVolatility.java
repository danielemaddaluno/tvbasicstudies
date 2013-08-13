/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "HV", title = "Historical Volatility", shortTitle = "HV")
public class StudyHistoricalVolatility extends Study
{
    @StudyArgInt(name="length", title="Length", defval=10, minval=1, maxval=1000)
    int length;

    @StudyPlot(name="HV")
    @StudyPlotStyle(title="HV", color="#3A85AD", lineStyle = CanvasEx.LineStyle.SOLID,
                                                 plotStyle = CanvasEx.PlotStyle.LINE)
    Series out;

    @Override
    public void init()
    {
        final double annual = 365;
        final Resolution resolution = barSet().src().resolution();
        double per = (resolution.isIntraday() ||(resolution.type() == Resolution.Type.daily && resolution.multiplier() == 1))
                      ? 1 : 7;

        final Series continuousReturns = continuousReturns(close());
        Series stdDev = stdDev(continuousReturns, length);
        // (stdDev * sqrt(annual/per)) * 100
        out = mul(mul(stdDev, sqrt(num(annual / per))), num(100));
    }

    private Series continuousReturns(Series src)
    {
        return log(div(src, offset(src, 1)));
    }
}
