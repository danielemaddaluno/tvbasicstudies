/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "DoubleEMA", isPriceStudy = true,
        title = "Double EMA", shortTitle = "DEMA")
public class StudyDEMA extends Study
{
    @StudyArgSourceLength(name = "Length", defval=9)
    int length;

    @StudyPlot(name = "DEMA")
    @StudyPlotStyle(title = "DEMA", color = "green")
    Series out;

    @Override
    public void init()
    {
        Series ema = ema(close(), length);
        Series ema_ema = ema(ema, length);
        out = diff(mul(num(2), ema), ema_ema);
    }
}
