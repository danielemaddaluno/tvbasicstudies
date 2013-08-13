/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "TripleEMA", isPriceStudy = true,
        title = "Triple EMA", shortTitle = "TEMA")
public class StudyTEMA extends Study
{
    @StudyArgSourceLength(name = "Length", defval=9)
    int length;

    @StudyPlot(name = "TEMA")
    @StudyPlotStyle(title = "TEMA", color = "green")
    Series out;

    @Override
    public void init()
    {
        Series ema = ema(close(), length);
        Series ema_ema = ema(ema, length);
        Series ema_ema_ema = ema(ema_ema, length);
        out = sum(mul(num(3), diff(ema, ema_ema)), ema_ema_ema);
    }
}
