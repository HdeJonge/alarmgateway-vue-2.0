<template>
  <div class="row">
    <div class="col-lg-12">
      <h4>Gateway List</h4>
          <router-link tag="button" to="/label/add-label" class="btn btn-primary" ><span class="glyphicon glyphicon-plus"></span> Add label</router-link>
      <table class="table">
        <thead>
          <tr>
            <th>id</th>
            <th>description</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="label in labels">
            <td>{{label.id}}</td>
            <td>{{label.description}}</td>
            <td>
              <router-link
                class="btn btn-info"
                :to="{ 
                  name: 'label-details',
                  params: { 
                      id:label.id,
                    p_label: label
                    }
                  }"
              >view</router-link>
              <router-link
                class="btn btn-warning"
                :to="{ 
                  name: 'label-edit',
                  params: { 
                      id:label.id,
                    p_label: label
                    }
                  }"
              >edit</router-link>
              <button class="btn btn-danger" @click="deleteLabel(label.id)">delete</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      labels: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    getLabels() {
      this.$http
        .get("label")
        .then(res => {
          return res.json();
        })
        .then(data => {
          console.log(data);
          const result = [];
          for (let key in data) {
            result.push(data[key]);
          }
          this.labels = result;
        });
    },
      deleteLabel(id) {
      this.$http
      .delete("label/" + id)
      .then(response=>{
        console.log("response:" + response),
        this.getLabels(),
        error=>{
          console.log("delete error:" + error);
        }
      });
    }
  },

    /* eslint-enable no-console */
  mounted() {
    this.getLabels();
  }
};
</script>
