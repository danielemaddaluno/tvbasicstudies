/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;
import studylib.sdk.StudyPlot;
import studylib.sdk.Study;

@StudyDecl(studyId = "ACCD", title = "Accumulation/Distribution", shortTitle = "Accum/Dist")
public class StudyAccDist extends Study
{
    @StudyPlot(name = "AccumulationDistribution")
    @StudyPlotStyle(title = "Accumulation/Distribution", color = "#999915")
    Series out;

    @Override
    public void init()
    {
        out = accdist();
    }
}
