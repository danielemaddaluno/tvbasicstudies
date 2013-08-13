/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "Trix", title = "TRIX", shortTitle = "TRIX")
@StudyBand(title = "Zero", value = 0.0, color = "#606060")
public class StudyTrix extends Study
{
    @StudyArgSourceLength(name = "length", defval=18)
    int length;

    @StudyPlot(name = "Trix")
    @StudyPlotStyle(title = "TRIX", color = "#991515")
    Series out;


    @Override
    public void init()
    {
//        1. Single-Smoothed EMA = 15-period EMA of the closing price
//        2. Double-Smoothed EMA = 15-period EMA of Single-Smoothed EMA
//        3. Triple-Smoothed EMA = 15-period EMA of Double-Smoothed EMA
//        4. TRIX = 1-period percent change in Triple-Smoothed EMA
        // i've made changes to this math as they were in C version originally crated by kblank
        out = mul(num(10000), change(ema(ema(ema(log(close()), length), length), length)));
    }
}
