/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;
import java.util.List;

@StudyDecl(studyId = "ENV", isPriceStudy = true, title = "Envelope", shortTitle = "Env")
@StudyArea(firstPlotName = "envlower", secondPlotName = "envupper",
           background = @StudyAreaBackground(color = "#0094ff"))
public class StudyEnvelope extends Study
{
    @StudyArgSourceLength(name="length", defval=20)
    int length;

    @StudyArgDouble(name="percent", defval=10.0)
    double percent;

    @StudyArgSource(name="source", defval=BarSet.Source.CLOSE)
    Series source;

    @StudyArgBool(name="exponential", defval=false)
    boolean exponential;

    @StudyPlot(name = "envbasis")
    @StudyPlotStyle(title = "Basis", color = "#ff6a00")
    Series basis;

    @StudyPlot(name = "envupper")
    @StudyPlotStyle(title = "Upper", color = "#0094ff")
    Series upper;

    @StudyPlot(name = "envlower")
    @StudyPlotStyle(title = "Lower", color = "#0094ff")
    Series lower;

    @Override
    public void init()
    {
        basis = esma(source, length, exponential);

        List<Series> bands = channel(basis, basis, percent / 100.0);
        lower = bands.get(0);
        upper = bands.get(1);
    }
}
