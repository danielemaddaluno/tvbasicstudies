/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "Stochastic", title = "Stochastic", shortTitle = "Stoch")
@StudyBands({@StudyBand(title = "Upper Band", value = 80.0, color = "#606060"),
             @StudyBand(title = "Lower Band", value = 20.0, color = "#606060")})
@StudyBandsBackground(color = "#9915ff", transparency = 80)
public class StudyStochastic extends Study
{
    @StudyArgSourceLength(name = "K", defval=14)
    int length;

    @StudyArgSourceLength(name = "D", defval=3)
    int smoothD;

    @StudyArgSourceLength(name = "smooth", defval=1)
    int smoothK;

    @StudyPlot(name = "K")
    @StudyPlotStyle(title = "K", color = "#0094ff")
    Series k;

    @StudyPlot(name = "D")
    @StudyPlotStyle(title = "D", color = "#ff6a00")
    Series d;


    @Override
    public void init()
    {
        k = sma(stoch(close(), high(), low(), length), smoothK);
        d = sma(k, smoothD);
    }
}
