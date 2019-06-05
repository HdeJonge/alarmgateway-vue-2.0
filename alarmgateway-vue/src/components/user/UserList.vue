<template>
  <div>
    <h4>User List</h4>
    <router-link tag="button" to="/gateway/add-gateway" class="btn btn-primary">
      <span class="glyphicon glyphicon-plus"></span> Add user
    </router-link>
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
    </v-data-table>
  </div>
</template>

<script>
export default {
  name: "datable1",
  data() {
    return {
      resource: {},
      total: 100,
      columns: ["id"],
      users: [],
      search: "",
      loading: true,
      pagination: { rowsPerPage: 10 },
      rowsPerPageItems: [10, 20, 50, 100],
      headers: [{ text: "id", value: "id" }, { text: "first name" },{},{}]
    };
  },
  watch: {
    pagination: {
      handler() {
        console.log("pag");
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
            columns: ["id", "firstName"]
          }
        })
        .then(res => {
          return res.json();
        })
        .then(data => {
          this.total = data.totalElements;
          const resultArray = [];
          for (let key in data.content) {
            resultArray.push(data.content[key]);
          }
          this.users = resultArray;
        });
      setTimeout(() => {
        this.loading = false;
      }, 500);
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