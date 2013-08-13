/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "VolatilityIndex", isPriceStudy = false,
        title = "Relative Volatility Index", shortTitle = "RVI")
@StudyOffset(min=-500, val=0, max=500)
@StudyBands({@StudyBand(title = "Upper Band", value = 80, color = "#c0c0c0"),
        @StudyBand(title = "Lower Band", value = 20, color = "#c0c0c0")})
@StudyBandsBackground(color = "#996a15")
public class StudyRelativeVolatilityIndex extends Study
{
    @StudyArgSourceLength(name = "Length", defval=10)
    int length;

    @StudyPlot(name = "RVI")
    @StudyPlotStyle(title = "RVI", color = "green")
    Series out;

    @Override
    public void init()
    {
        Series src = close();
        Series prev = offset(src, 1);
        Series stddev = stdDev(src, length);
        int len = 14;
        Series rviUp = ema(replace(stddev, 0, le(src, prev)), len);
        Series rviDown = ema(replace(stddev, 0, gt(src, prev)), len);
        out = percent(rviUp, sum(rviUp, rviDown));
    }
}
