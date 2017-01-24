package ml224ec_assign1;

import java.util.ArrayList;
import java.util.List;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;

public class SinMain {

	public static void main(String[] args) {
		
		XYChart chart = new XYChartBuilder().width(800).height(600).build();
		chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line);
		chart.getStyler().setMarkerSize(1);
		
		List<Double> xData = new ArrayList<Double>();
		List<Double> yData = new ArrayList<Double>();
		
		double lowerLimit = 0.0;
		double upperLimit = 2 * Math.PI;
		double xValue = lowerLimit;
		
		while ((xValue += 0.01) <= upperLimit)
		{
			xData.add(xValue);
			yData.add(calculateY(xValue));
		}
		
		chart.addSeries("Cosine curve", xData, yData);
		new SwingWrapper(chart).displayChart();
	}
	
	private static double calculateY(double x)
	{
		return (1 + x/Math.PI)*Math.cos(x)*Math.cos(40*x);
	}

}
