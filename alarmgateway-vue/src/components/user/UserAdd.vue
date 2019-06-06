<template>
  <div class="row">
    <div class="col-lg-12">
      <h4>New User</h4>
      <div v-if="errorMessage.length>0" class="alert alert-danger" role="alert">
        <p>{{errorMessage}}</p>
      </div>
      <div v-if="errors.length>0" class="alert alert-danger" role="alert">
        <ul>
          <li v-for="error in errors">{{error.field + " " + error.defaultMessage}}</li>
        </ul>
      </div>
      <div class="form-group">
        <label>first name</label>
        <input class="form-control" type="text" v-model="user.firstName">
      </div>
      <div class="form-group">
        <label>last name</label>
        <input class="form-control" type="text" v-model="user.lastName">
      </div>
      <div class="form-group">
        <label>birth day</label>
        <input class="form-control" type="date" v-model="user.birthDay">
      </div>
      <button v-on:click="saveData" class="btn btn-success">Submit</button>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      user: {
        id: 0,
        firstName: "",
        lastName: "",
        birthDay: null
      },
      resource: {},
      errors: [],
      errorMessage: ""
    };
  },
  methods: {
    saveData() {
      this.resource
        .save(this.user)
        .then(response => {
          console.log("save response: " + response),
            error => {
              console.log(error);
            };
          this.return();
        })
        .catch(error => {
          if (error.body.errors == undefined) {
            this.errorMessage = error.body.message;
          } else {
            this.errors = error.body.errors;
          }
        });
    },
    return() {
      this.$router.push("/user");
    }
  },
  created() {
    this.resource = this.$resource("user/");
  }
};
</script>