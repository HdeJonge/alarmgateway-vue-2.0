<template>
  <div>
    <h4>User List</h4>
    <h2>Fetch Example</h2>
    <downloadexcel
      class="btn"
      :fetch="fetchData"
      :fields="json_fields"
      :before-generate="startDownload"
      :before-finish="finishDownload"
      type="xlsx"
    >Download Excel</downloadexcel>
    <p></p>
    <router-link tag="button" to="/user/add-user" class="btn btn-primary">
      <span class="glyphicon glyphicon-plus"></span> Add uzser
    </router-link>
    <hr>
    <label for="from">from</label>
    <input id="from" @input="retrieveUsers" type="date" v-model="from">
    <label for="to">to</label>
    <input id="to" @input="retrieveUsers" type="date" v-model="to">
    <hr>
    <v-text-field @input="retrieveUsers" label="search" v-model="search"></v-text-field>
    <v-data-table
      :headers="headers"
      :items="users"
      :rows-per-page-items="rowsPerPageItems"
      :pagination.sync="pagination"
      :total-items="total"
      :loading="loading"
      class="elevation-1"
    >
      <template v-slot:items="props">
        <td>{{ props.item.id }}</td>
        <td class="text-xs-left">{{ props.item.firstName }}</td>
        <td class="text-xs-left">{{ props.item.birthDay | moment("dddd, MMMM Do YYYY") }}</td>
        <td class="text-xs-left">{{ "2019-6-4 17:00:00" | moment("from", "now", true) }}</td>
      </template>
    </v-data-table>
  </div>
</template>




<script>
import axios from "axios";
import downloadexcel from "vue-json-excel";
export default {
  name: "datable1",
  components: {
    downloadexcel,
  },
  data() {
    return {
      // from: new Date().toISOString().substr(0, 10),
      // to: new Date().toISOString().substr(0, 10),
      from: "",
      to: "",
      resource: {},
      total: 100,
      columns: ["id"],
      users: [],
      search: "",
      loading: true,
      pagination: { rowsPerPage: 10 },
      rowsPerPageItems: [10, 20, 50, 100],
      headers: [{ text: "id", value: "id" }, { text: "first name" }, {}, {}],

      json_fields: {
        id: "id",
        name: "firstName"
      }
    };
  },
  watch: {
    pagination: {
      handler() {
        this.retrieveUsers();
      },
      deep: true
    }
  },
  mounted() {
    console.log("mount");
    this.retrieveUsers();
  },
  methods: {
    retrieveUsers() {
      console.log("retr");
      this.loading = true;
      this.resource
        .get({
          query: {
            search: this.search,
            desc: this.pagination.descending,
            sortBy: this.pagination.sortBy,
            page: this.pagination.page,
            size: this.pagination.rowsPerPage,
            columns: ["id", "firstName"],
            from: this.from,
            to: this.to
          }
        })
        .then(res => {
          return res.json();
        })
        .then(data => {
          console.log(data);
          this.total = data.totalElements;
          const resultArray = [];
          for (let key in data.content) {
            resultArray.push(data.content[key]);
          }
          const { userlist } = data.content;
          this.users = resultArray;
        });
      setTimeout(() => {
        this.loading = false;
      }, 500);
    },
    async fetchData() {
      // const response = await axios.get(
      //   "https://holidayapi.com/v1/holidays?key=a4b2083b-1577-4acd-9408-6e529996b129&country=US&year=2018&month=09"
      // );
      const response = await this.resource
        .get({
          query: {
            search: this.search,
            desc: this.pagination.descending,
            sortBy: this.pagination.sortBy,
            page: this.pagination.page,
            size: this.pagination.rowsPerPage,
            columns: ["id", "firstName"],
            from: this.from,
            to: this.to
          }
        })
        .then(res => {
          return res.json();
        });
      console.log(response);
      return response.content;
    },
    startDownload() {
      alert("show loading");
    },
    finishDownload() {
      alert("hide loading");
    }
  },
  created() {
    this.resource = this.$resource("user/{?query*}");
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