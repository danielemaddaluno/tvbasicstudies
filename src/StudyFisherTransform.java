/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;
import studylib.sdk.studies.SingleSeriesExpr;

@StudyDecl(studyId = "FisherTransform", title = "Fisher Transform", shortTitle = "Fisher")
@StudyBands({
    @StudyBand(title = "1.5", value = 1.5, color = "#ffc5b5"),
    @StudyBand(title = "0.75", value = 0.75, color = "#c6c6c6"),
    @StudyBand(title = "0", value = 0.0, color = "#ffc5b5"),
    @StudyBand(title = "-0.75", value = -0.75, color = "#c6c6c6"),
    @StudyBand(title = "-1.5", value = -1.5, color = "#ffc5b5")
})
public class StudyFisherTransform extends Study
{
    @StudyArgInt(name = "length", minval=5, maxval=1000, defval=9)
    int length;

    @StudyPlot(name = "F1")
    @StudyPlotStyle(title = "Fisher", color = "#0094ff")
    Series outF1;

    @StudyPlot(name = "F2")
    @StudyPlotStyle(title = "Trigger", color = "#ff6a00")
    Series outF2;

    @Override
    public void init()
    {
        outF1 = newSeries(new SingleSeriesExpr(barSet())
        {
            final Series HL2 = using(hl2());
            final Series minHL2 = using(lowest(HL2, length));
            final Series maxHL2 = using(highest(HL2, length));
            LocalVar<Double> fish1Prev = newDouble(0);
            LocalVar<Double> value1Prev = newDouble(0);

            @Override
            public double calc(int i, boolean isNewVal)
            {
                final double price = HL2.at(i);
                final double maxHL2 = this.maxHL2.at(i);
                final double minHL2 = this.minHL2.at(i);
                if (Double.isNaN(price) || Double.isNaN(maxHL2) || Double.isNaN(minHL2))
                {
                    return Double.NaN;
                }

                final double maxHL2MinusMinHL2 = Math.max(maxHL2 - minHL2, .001);

                double value1 = .66 * ((price - minHL2) / maxHL2MinusMinHL2 - .5) + .67 * value1Prev.get();
                value1 = (value1 > .99) ? .999 : value1;
                value1 = (value1 < -.99) ? -.999 : value1;

                final double fish1 = .5 * Math.log((1 + value1) / Math.max(1 - value1, .001)) + .5 * fish1Prev.get();

                value1Prev.set(value1);
                fish1Prev.set(fish1);

                return fish1;
            }
        });

        outF2 = offset(outF1, 1);
    }
}
