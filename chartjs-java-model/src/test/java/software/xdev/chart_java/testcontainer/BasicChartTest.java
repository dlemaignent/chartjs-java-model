/*
 * Copyright © 2023 XDEV Software (https://xdev.software)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package software.xdev.chart_java.testcontainer;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import software.xdev.chart_java.charts.BarChart;
import software.xdev.chart_java.charts.BubbleChart;
import software.xdev.chart_java.charts.Chart;
import software.xdev.chart_java.charts.DoughnutChart;
import software.xdev.chart_java.charts.LineChart;
import software.xdev.chart_java.charts.PieChart;
import software.xdev.chart_java.charts.PolarChart;
import software.xdev.chart_java.charts.RadarChart;
import software.xdev.chart_java.charts.ScatterChart;
import software.xdev.chart_java.data.BarData;
import software.xdev.chart_java.data.BubbleData;
import software.xdev.chart_java.data.Data;
import software.xdev.chart_java.data.DoughnutData;
import software.xdev.chart_java.data.LineData;
import software.xdev.chart_java.data.PieData;
import software.xdev.chart_java.data.PolarData;
import software.xdev.chart_java.data.RadarData;
import software.xdev.chart_java.data.ScatterData;
import software.xdev.chart_java.datapoint.BubbleDataPoint;
import software.xdev.chart_java.datapoint.ScatterDataPoint;
import software.xdev.chart_java.dataset.BarDataset;
import software.xdev.chart_java.dataset.BubbleDataset;
import software.xdev.chart_java.dataset.DoughnutDataset;
import software.xdev.chart_java.dataset.LineDataset;
import software.xdev.chart_java.dataset.PieDataset;
import software.xdev.chart_java.dataset.PolarDataset;
import software.xdev.chart_java.dataset.RadarDataset;
import software.xdev.chart_java.dataset.ScatterDataset;
import software.xdev.chart_java.options.BarOptions;
import software.xdev.chart_java.options.BubbleOptions;
import software.xdev.chart_java.options.DoughnutOptions;
import software.xdev.chart_java.options.LineOptions;
import software.xdev.chart_java.options.Options;
import software.xdev.chart_java.options.PieOptions;
import software.xdev.chart_java.options.PolarOptions;
import software.xdev.chart_java.options.RadarOptions;


@SuppressWarnings("java:S2699") // Done in custom method
class BasicChartTest extends AbstractChartTest
{
	@ParameterizedTest(name = "Check if basic {0} is displayed correctly")
	@MethodSource
	<O extends Options<O, ?>, D extends Data<D, ?, ?>> void basicTest(
		final String chartName,
		final Chart<?, O, D> chart,
		final O options,
		final D data)
	{
		options.setAnimation(false);
		
		chart.setOptions(options);
		chart.setData(data);
		
		this.createScreenshotAndCompare(
			chart,
			this.getWebContainer(),
			"Basic");
	}
	
	static Stream<Arguments> basicTest()
	{
		final Set<Map.Entry<String, BigDecimal>> defaultDataEntrySet = IntStream.rangeClosed(0, 3)
			.boxed()
			.collect(Collectors.toMap(String::valueOf, BigDecimal::valueOf))
			.entrySet();
		
		return Stream.of(
			new ArgumentDTO<>(
				BarChart::new,
				BarOptions::new,
				() -> {
					final BarDataset dataset = new BarDataset();
					final BarData data = new BarData();
					data.addDataset(dataset);
					for(final Map.Entry<String, BigDecimal> entry : defaultDataEntrySet)
					{
						data.addLabel(entry.getKey());
						dataset.addData(entry.getValue());
					}
					return data;
				}),
			new ArgumentDTO<>(
				BubbleChart::new,
				BubbleOptions::new,
				() -> {
					final BubbleDataset dataset = new BubbleDataset();
					final BubbleData data = new BubbleData();
					data.addDataset(dataset);
					for(final Map.Entry<String, BigDecimal> entry : defaultDataEntrySet)
					{
						data.addLabel(entry.getKey());
						dataset.addData(
							new BubbleDataPoint(
								entry.getValue(),
								entry.getValue(),
								entry.getValue().multiply(BigDecimal.TEN)));
					}
					return data;
				}),
			new ArgumentDTO<>(
				DoughnutChart::new,
				DoughnutOptions::new,
				() -> {
					final DoughnutDataset dataset = new DoughnutDataset();
					final DoughnutData data = new DoughnutData();
					data.addDataset(dataset);
					for(final Map.Entry<String, BigDecimal> entry : defaultDataEntrySet)
					{
						data.addLabel(entry.getKey());
						dataset.addData(entry.getValue());
					}
					return data;
				}),
			new ArgumentDTO<>(
				LineChart::new,
				LineOptions::new,
				() -> {
					final LineDataset dataset = new LineDataset();
					final LineData data = new LineData();
					data.addDataset(dataset);
					for(final Map.Entry<String, BigDecimal> entry : defaultDataEntrySet)
					{
						data.addLabel(entry.getKey());
						dataset.addData(entry.getValue());
					}
					return data;
				}),
			new ArgumentDTO<>(
				PieChart::new,
				PieOptions::new,
				() -> {
					final PieDataset dataset = new PieDataset();
					final PieData data = new PieData();
					data.addDataset(dataset);
					for(final Map.Entry<String, BigDecimal> entry : defaultDataEntrySet)
					{
						data.addLabel(entry.getKey());
						dataset.addData(entry.getValue());
					}
					return data;
				}),
			new ArgumentDTO<>(
				PolarChart::new,
				PolarOptions::new,
				() -> {
					final PolarDataset dataset = new PolarDataset();
					final PolarData data = new PolarData();
					data.addDataset(dataset);
					for(final Map.Entry<String, BigDecimal> entry : defaultDataEntrySet)
					{
						data.addLabel(entry.getKey());
						dataset.addData(entry.getValue());
					}
					return data;
				}),
			new ArgumentDTO<>(
				RadarChart::new,
				RadarOptions::new,
				() -> {
					final RadarDataset dataset = new RadarDataset();
					final RadarData data = new RadarData();
					data.addDataset(dataset);
					for(final Map.Entry<String, BigDecimal> entry : defaultDataEntrySet)
					{
						data.addLabel(entry.getKey());
						dataset.addData(entry.getValue());
					}
					return data;
				}),
			new ArgumentDTO<>(
				ScatterChart::new,
				LineOptions::new,
				() -> {
					final ScatterDataset dataset = new ScatterDataset();
					final ScatterData data = new ScatterData();
					data.addDataset(dataset);
					for(final Map.Entry<String, BigDecimal> entry : defaultDataEntrySet)
					{
						data.addLabel(entry.getKey());
						dataset.addData(new ScatterDataPoint(entry.getValue(), entry.getValue()));
					}
					return data;
				})
		).map(dto ->
			Arguments.of(
				dto.chartSupplier().get().getClass().getSimpleName(),
				dto.chartSupplier().get(),
				dto.optionsSupplier().get(),
				dto.dataSupplier().get()));
	}
	
	record ArgumentDTO<O extends Options<O, ?>, D extends Data<D, ?, ?>>(
		Supplier<Chart<?, O, D>> chartSupplier,
		Supplier<O> optionsSupplier,
		Supplier<D> dataSupplier
	)
	{
	}
}
