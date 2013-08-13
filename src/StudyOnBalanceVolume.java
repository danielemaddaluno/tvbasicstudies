/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "OBV", title = "On Balance Volume", shortTitle = "OBV")
public class StudyOnBalanceVolume extends Study
{
    @StudyPlot(name = "OnBalanceVolume")
    @StudyPlotStyle(title = "", color = "#3a6ca8")
    Series out;

    @Override
    public void init()
    {
        final Series src = close();
        final Series prev = offset(src, 1);
        // zero must take into account case when volume is NaN, in this case it must also be NaN
        final Series zero = mul(num(0), volume());
        out = accum(
                swtch(
                    volume(), gt(src, prev),
                    neg(volume()), gt(prev, src),
                    zero));
    }
}
