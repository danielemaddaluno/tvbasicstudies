/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "PSAR", isPriceStudy = true, title = "Parabolic SAR", shortTitle = "SAR")
public class StudyParabolicSAR extends Study
{
    @StudyArgDouble(name = "start", defval=0.02)
    double start;

    @StudyArgDouble(name = "increment", defval=0.02)
    double increment;

    @StudyArgDouble(name = "max value", defval=0.2)
    double maximum;

    @StudyPlot(name = "ParabolicSAR")
    @StudyPlotStyle(title = "", color = "#3a6ca8", plotStyle = CanvasEx.PlotStyle.CROSS)
    Series out;

    @Override
    public void init()
    {
        out = psar(Math.round(1000 * start), Math.round(1000 * increment), Math.round(1000 * maximum));
    }
}
