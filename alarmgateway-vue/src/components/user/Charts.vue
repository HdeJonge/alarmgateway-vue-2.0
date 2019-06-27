<template>
  <div class="container">
    <line-chart v-if="loaded" :chartdata="chartdata" :options="options"/>
    <p>{{chartdata}}</p>
  </div>
</template>
<script>
import Bar from "./BarChart.vue";

export default {
  name: "LineChartContainer",
  components: { "line-chart": Bar },
  data: () => ({
    somedata: null,
    loaded: false,
    chartdata: {
      labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
      datasets: [
        {
          label: "Test data",
          backgroundColor: '#f87979',
          data: []
        }
      ]
    },
    options: { responsive: true, maintainAspectRatio: false }
  }),
  mounted() {
    this.loaded = false;
    try {
      this.$http
        .get("http://localhost:8080/api/alarm")
        .then(res => {
          return res.json();
        })
        .then(data => {
          console.log(data);
          const result = [];
          for (let key in data) {
            result.push(data[key].id);
          }
          this.chartdata.datasets[0].data = result;
          this.loaded = true;
        });
    } catch (e) {
      console.error(e);
    }
  }
};
</script>