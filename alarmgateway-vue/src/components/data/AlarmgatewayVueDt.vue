<template>
  <div>
    <h1>The Home Page</h1>
    <hr>
    <p>
      Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab asperiores, aut autem, blanditiis consequuntur
      dolore excepturi laborum maiores minima nihil non nulla obcaecati quas quibusdam quod sed suscipit vero
      voluptatem.
    </p>
    <p>p: {{pagination}}</p>
    <p>loading :{{loading}}</p>
    <p>search: {{search}}</p>
    <v-text-field @input="retrieveGateways" label="search" v-model="search"></v-text-field>
    <v-data-table
      :headers="headers"
      :items="gateways"
      :rows-per-page-items="rowsPerPageItems"
      :pagination.sync="pagination"
      :total-items="total"
      :loading="loading"
      class="elevation-1"
    >
      <template v-slot:items="props">
        <td>{{ props.item.id }}</td>
        <td class="text-xs-left">{{ props.item.mac }}</td>
      </template>
    </v-data-table>
  </div>
</template>

<script>
export default {
  name: "datable1",
  data() {
    return {
      total: 100,
      columns:["id", "mac"],
      desserts: [],
      gateways: [],
      search: "",
      loading: true,
      pagination: { rowsPerPage: 10 },
      rowsPerPageItems: [10, 20, 50, 100],
      headers: [{ text: "id", value: "id" }, { text: "mac", value: "mac" }]
    };
  },
  watch: {
    pagination: {
      handler() {
        this.retrieveGateways();
      },
      deep: true
    }
  },
  mounted() {
    this.retrieveGateways();
  },
  methods: {
    retrieveGateways() {
      console.log("retr");
      this.loading = true;
      this.$http
        .get(
          "datatable/get?search=" +
            this.search +
            "&direction=" +
            this.pagination.descending +
            "&sortBy=" +
            this.pagination.sortBy +
            "&page=" +
            this.pagination.page +
            "&size=" +
            this.pagination.rowsPerPage +
            "&columns=" +
            this.columns
        )
        .then(res => {
          return res.json();
        })
        .then(data => {
          console.log(data);
          this.total = data.totalElements;
          //this.gateways = data;
          const resultArray = [];
          for (let key in data.content) {
            resultArray.push(data.content[key]);
          }
          this.gateways = resultArray;
        });
      setTimeout(() => {
        this.loading = false;
      }, 500);
    },
    getDataFromApi() {
      this.loading = true;
      return new Promise((resolve, reject) => {
        const { sortBy, descending, page, rowsPerPage } = this.pagination;

        let items = this.getDesserts();
        const total = items.length;

        if (this.pagination.sortBy) {
          items = items.sort((a, b) => {
            const sortA = a[sortBy];
            const sortB = b[sortBy];

            if (descending) {
              if (sortA < sortB) return 1;
              if (sortA > sortB) return -1;
              return 0;
            } else {
              if (sortA < sortB) return -1;
              if (sortA > sortB) return 1;
              return 0;
            }
          });
        }

        if (rowsPerPage > 0) {
          items = items.slice((page - 1) * rowsPerPage, page * rowsPerPage);
        }

        setTimeout(() => {
          this.loading = false;
          resolve({
            items,
            total
          });
        }, 1000);
      });
    },
    getDesserts() {
      return [
        {
          name: "Frozen Yogurt",
          calories: 159,
          fat: 6.0,
          carbs: 24,
          protein: 4.0,
          iron: "1%"
        },
        {
          name: "Ice cream sandwich",
          calories: 237,
          fat: 9.0,
          carbs: 37,
          protein: 4.3,
          iron: "1%"
        },
        {
          name: "Eclair",
          calories: 262,
          fat: 16.0,
          carbs: 23,
          protein: 6.0,
          iron: "7%"
        },
        {
          name: "Cupcake",
          calories: 305,
          fat: 3.7,
          carbs: 67,
          protein: 4.3,
          iron: "8%"
        },
        {
          name: "Gingerbread",
          calories: 356,
          fat: 16.0,
          carbs: 49,
          protein: 3.9,
          iron: "16%"
        },
        {
          name: "Jelly bean",
          calories: 375,
          fat: 0.0,
          carbs: 94,
          protein: 0.0,
          iron: "0%"
        },
        {
          name: "Lollipop",
          calories: 392,
          fat: 0.2,
          carbs: 98,
          protein: 0,
          iron: "2%"
        },
        {
          name: "Honeycomb",
          calories: 408,
          fat: 3.2,
          carbs: 87,
          protein: 6.5,
          iron: "45%"
        },
        {
          name: "Donut",
          calories: 452,
          fat: 25.0,
          carbs: 51,
          protein: 4.9,
          iron: "22%"
        },
        {
          name: "KitKat",
          calories: 518,
          fat: 26.0,
          carbs: 65,
          protein: 7,
          iron: "6%"
        }
      ];
    }
  }
};
</script>
 
<style>
.list {
  text-align: left;
  max-width: 450px;
  margin: auto;
}
</style>