/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;
import studylib.sdk.studies.Calc;
import studylib.sdk.studies.SingleSeriesExpr;

@StudyDecl(studyId = "CRSI", title = "ConnorsRSI", shortTitle = "CRSI")
@StudyBands({
    @StudyBand(title = "Upper Band", value = 70, color = "#c0c0c0"),
    @StudyBand(title = "Lower Band", value = 30, color = "#c0c0c0")})
@StudyBandsBackground(color = "#9915ff")
public class StudyConnorsRSI extends Study
{
    @StudyArgSourceLength(name = "LenRSI", title = "RSI Length", defval = 3)
    int iLenRSI;
    @StudyArgSourceLength(name = "LenUP", title = "UpDown Length", defval = 2)
    int iLenUP;
    @StudyArgSourceLength(name = "LenROC", title = "ROC Length", defval = 100)
    int iLenROC;

    @StudyPlot(name="crsi")
    @StudyPlotStyle(title="CRSI", color = "#3A78AA")
    Series CRSI;

    @Override
    public void init()
    {
        // TODO : move to StdLib
        // formula ported from TradeStation
        final Series src = close();
        Series UpDownCount = countUpDown(src);
        Series RSI = rsi(src, iLenRSI);
        Series UpDownRSI = rsi(UpDownCount,iLenUP);
        Series PercentROC = percentRank(roc(src, 1), iLenROC);
        CRSI = avg(RSI, UpDownRSI, PercentROC);
    }

    Series countUpDown(final Series source)
    {
        return newSeries(new SingleSeriesExpr(barSet(), 2)
        {
            final Series src = using(source, 2);

            @Override
            public double calc(int i, boolean isNewVal)
            {
                if (i < 2)
                {
                    return Double.NaN;
                }

                final double cur = src.at(i);
                final double prev = src.at(i-1);
                if (Calc.isZero(cur, prev))
                {
                    return 0;
                }
                final double old = out.at(i-1);
                if (Calc.isGreater(cur, prev))
                {
                    return Calc.isLessOrEqual(old, 0) ? 1 : old + 1;
                }
                else
                {
                    return Calc.isGreaterOrEqual(old, 0) ? -1 : old - 1;
                }
            }
        });

    }
}
