/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "StochasticRSI", title = "Stochastic RSI", shortTitle = "Stoch RSI")
@StudyBands({@StudyBand(title = "Upper Band", value = 80.0, color = "#606060"),
             @StudyBand(title = "Lower Band", value = 20.0, color = "#606060")})
@StudyBandsBackground(color = "#9915ff", transparency = 80)
public class StudyStochasticRSI extends Study
{
    @StudyArgSourceLength(name = "K", defval=3, title = "K")
    int smoothK;

    @StudyArgSourceLength(name = "D", defval=3, title = "D")
    int smoothD;

    @StudyArgSourceLength(name = "RSI Length", defval=14, title = "RSI Length")
    int lengthRSI;

    @StudyArgSourceLength(name = "Stochastic Length", defval=14, title = "Stochastic Length")
    int lengthStoch;

    @StudyArgSource(name = "RSI Source", defval= BarSet.Source.CLOSE, title = "RSI Source")
    Series source;

    @StudyPlot(name = "K")
    @StudyPlotStyle(title = "K", color = "#0094ff")
    Series k;

    @StudyPlot(name = "D")
    @StudyPlotStyle(title = "D", color = "#ff6a00")
    Series d;

    Series rsi;


    @Override
    public void init()
    {
        rsi = rsi(source, lengthRSI);
        k = sma(stoch(rsi, rsi, rsi, lengthStoch), smoothK);
        d = sma(k, smoothD);
    }
}
