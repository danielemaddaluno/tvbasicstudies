/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "EaseOfMovement", title = "Ease Of Movement", shortTitle = "EOM")
public class StudyEaseOfMovement extends Study
{
    @StudyArgSourceLength(name = "length", defval=14)
    int length;

    @StudyArgInt(name = "divisor", minval=1, maxval=1000000000, defval=10000)
    int divisor;

    @StudyPlot(name = "EaseOfMovement")
    @StudyPlotStyle(title = "EOM", color = "#999915")
    Series outEOM;

    @Override
    public void init()
    {
        final Series hl2 = avg(high(), low());
        final Series ch = change(hl2);
        final Series di = div(volume(), diff(high(), low()));
        final Series eom = percent(ch, di, divisor);
        outEOM = sma(eom, length);
    }
}
