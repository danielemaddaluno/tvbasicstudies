/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "KST", title = "Know Sure Thing", shortTitle = "KST")
@StudyBand(title = "Zero Line", value = 0, color = "#c0c0c0")
public class StudyKST extends Study
{
    @StudyArgSourceLength(name = "ROCLen1", defval=10)
    int rocLen1;
    @StudyArgSourceLength(name = "ROCLen2", defval=15)
    int rocLen2;
    @StudyArgSourceLength(name = "ROCLen3", defval=20)
    int rocLen3;
    @StudyArgSourceLength(name = "ROCLen4", defval=30)
    int rocLen4;

    @StudyArgSourceLength(name = "SMALen1", defval=10)
    int smaLen1;
    @StudyArgSourceLength(name = "SMALen2", defval=10)
    int smaLen2;
    @StudyArgSourceLength(name = "SMALen3", defval=10)
    int smaLen3;
    @StudyArgSourceLength(name = "SMALen4", defval=15)
    int smaLen4;

    @StudyArgSourceLength(name = "SigLen", defval=9)
    int sigLen;

    @StudyPlot(name = "KST")
    @StudyPlotStyle(title = "KST", color = "green")
    Series KST;

    @StudyPlot(name = "SIG")
    @StudyPlotStyle(title = "Sig", color = "red")
    Series SignalLine;

    @Override
    public void init()
    {
        Series RCMA1 = sma(roc(close(), rocLen1), smaLen1);
        Series RCMA2 = sma(roc(close(), rocLen2), smaLen2);
        Series RCMA3 = sma(roc(close(), rocLen3), smaLen3);
        Series RCMA4 = sma(roc(close(), rocLen4), smaLen4);

        KST = sum(
                RCMA1,
                mul(RCMA2, num(2)),
                mul(RCMA3, num(3)),
                mul(RCMA4, num(4)));

        SignalLine = sma(KST, sigLen);
    }
}
