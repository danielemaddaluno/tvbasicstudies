/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;
import java.util.List;

@StudyDecl(studyId = "MACD", title = "MACD", shortTitle = "MACD")
public class StudyMACD extends Study
{
    @StudyArgSourceLength(name = "fast length", defval=12)
    int fastLength;

    @StudyArgSourceLength(name = "slow length", defval=26)
    int slowLength;

    @StudyArgSource(name = "source", defval= BarSet.Source.CLOSE)
    Series source;

    @StudyArgInt(name = "signal smoothing", minval=1, maxval=50, defval=9)
    int signalLength;

    @StudyArgBool(name = "simple ma(oscillator)", defval=false)
    boolean simpleMaOsc;

    @StudyArgBool(name = "simple ma(signal line)", defval=true)
    boolean simpleMaSignal;

    Series fastMA;
    Series slowMA;

    @StudyPlot(name = "histogram")
    @StudyPlotStyle(title = "Histogram", color = "#ff006e", plotStyle = CanvasEx.PlotStyle.HISTOGRAM)
    Series hist;

    @StudyPlot(name = "macd")
    @StudyPlotStyle(title = "MACD", color = "#0094ff")
    Series macd;

    @StudyPlot(name = "signal")
    @StudyPlotStyle(title = "Signal", color = "#ff6a00")
    Series signal;

    @Override
    public void init()
    {
        List<Series> res = macd(source, fastLength, slowLength, signalLength, simpleMaOsc, simpleMaSignal);
        fastMA = res.get(0);
        slowMA = res.get(1);
        macd = res.get(2);
        signal = res.get(3);
        hist = res.get(4);
    }
}
