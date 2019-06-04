<template>
  <div class="row">
    <div class="col-lg-12">
      <h4>New label</h4>
      <div v-if="errors.length>0" class="alert alert-danger" role="alert">
        <ul>
          <li v-for="error in errors">{{error.field + " " + error.defaultMessage}}</li>
        </ul>
      </div>
      <div class="form-group">
        <label>description</label>
        <input class="form-control" type="text" v-model="label.description">
      </div>
      <button v-on:click="saveData" class="btn btn-success">Submit</button>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      label: {
        id: 0,
        description: ""
      },
      resource: {},
      errors: []
    };
  },
  methods: {
    saveData() {
      this.resource
        .save(this.label)
        .then(response => {
          console.log("save response: " + response),
            error => {
              console.log(error);
            };
          this.return();
        })
        .catch(error => {
          this.errors = error.body.errors;
        });
    },
    return() {
      this.$router.push("/label");
    }
  },
  created() {
    this.resource = this.$resource("label/");
  }
};
</script>