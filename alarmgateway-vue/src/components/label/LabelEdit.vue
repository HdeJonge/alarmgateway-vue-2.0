
<template>
  <div class="row">
    <div class="col-lg-12">
      <h4>Edit label</h4>
      <div class="form-group">
        <label>label|{{p_label.id}}</label>
        <input class="form-control" type="text" v-model="p_label.description">
      </div>
      <button v-on:click="updateData" class="btn btn-success">Update</button>
    </div>
  </div>
</template>
<script>
export default {
  props: ["p_label"],
  methods: {
    updateData(){
      this.$http
      .put("label/" + this.p_label.id, this.p_label)
      .then(response=>{
        this.return();
        console.log(response.data),
        error=>{
          console.log("put error: " + error);
        };
      });
    },
    return(){
      this.$router.push('/label');
      //this.$router.push({path:'/'});
    }
  }
};
</script>