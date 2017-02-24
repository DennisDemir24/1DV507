package ml224ec_assign3.histogram_revisited;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class HistogramPresentation extends GridPane 
{	
	private BarChart<Number,String> barChart;
	private PieChart pieChart;
	
	private int totalReadIntegers = 0;
	private int displayedIntervals = 0;
	private int culledIntervals = 0; 
	
	public HistogramPresentation()
	{
		NumberAxis xAxis = new NumberAxis();
		CategoryAxis yAxis = new CategoryAxis();
		barChart = new BarChart<>(xAxis,yAxis);
		barChart.setTitle("Histogram - Bar chart");
		
		pieChart = new PieChart();
		pieChart.setTitle("Histogram - Pie chart");
		
		this.add(barChart, 0, 0);
		this.add(pieChart, 1, 0);
	}
	
	public void updateDataFrom(String filepath) throws Exception
	{
		List<List<Integer>> data = Histogram.readFromFile(filepath);
		
		List<XYChart.Series<Number, String>> barChartData = 
				new ArrayList<XYChart.Series<Number,String>>();
		ObservableList<PieChart.Data> pieChartData = 
				FXCollections.observableArrayList();
		
		totalReadIntegers = displayedIntervals = culledIntervals = 0;
		for (int i = 0; i < data.size(); i++)
		{
			String categoryName;
			
			if (i < 1)
				categoryName = "Other";
			else
				categoryName = String.format("%d - %d", i*10 + 1, i*10 + 10);
			
			int count = data.get(i).size();
			
			totalReadIntegers += count;
			
			if (count > 0)
			{
				displayedIntervals++;
				XYChart.Series<Number, String> barData = new Series<Number, String>();
				barData.setName(categoryName);
				
				barData.getData().add(new XYChart.Data<Number, String>(count, ""));
				barChartData.add(barData);
				pieChartData.add(new PieChart.Data(categoryName, count));
			}
			else 
				culledIntervals++;
		}
		
		barChart.getData().clear();
		barChart.getData().addAll(barChartData);
		
		pieChart.getData().clear();
		pieChart.getData().addAll(pieChartData);
	}

	public int getTotalReadIntegers() {
		return totalReadIntegers;
	}
	
	public int getDisplayedIntervals() {
		return displayedIntervals;
	}
	
	public int getCulledIntervals() {
		return culledIntervals;
	}
}
