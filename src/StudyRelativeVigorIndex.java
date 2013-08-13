/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "VigorIndex", isPriceStudy = false,
        title = "Relative Vigor Index", shortTitle = "RVGI")
@StudyOffset(min=-500, val=0, max=500)
public class StudyRelativeVigorIndex extends Study
{
    @StudyArgSourceLength(name = "length", defval=10)
    int length;

    @StudyPlot(name = "RVGI")
    @StudyPlotStyle(title = "RVGI", color = "green")
    Series rvi;

    @StudyPlot(name = "SIG")
    @StudyPlotStyle(title = "Signal", color = "red")
    Series sig;

    @Override
    public void init()
    {
        rvi = div(sum(swma(diff(close(), open())), length), sum(swma(diff(high(), low())), length));
        sig = swma(rvi);
    }
}
