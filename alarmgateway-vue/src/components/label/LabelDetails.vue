<template>
  <div class="row">
    <div class="col-lg-12">
      <h4>Label details</h4>
      <div>
        <label>id:</label>
        {{this.p_label.id}}
      </div>
      <div>
        <label>description:</label>
        {{this.p_label.description}}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["p_label"]
};
</script>