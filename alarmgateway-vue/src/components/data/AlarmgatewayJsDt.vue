<template>
  <div>
    <h1>The Home Page</h1>
    <hr>
    <p>
      Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab asperiores, aut autem, blanditiis consequuntur
      dolore excepturi laborum maiores minima nihil non nulla obcaecati quas quibusdam quod sed suscipit vero
      voluptatem.
    </p>
    <table id="gateways" class="table table-striped table-bordered" style="width: 100%">
      <thead>
        <tr>
          <th>MAC</th>
          <th>profile</th>
        </tr>
      </thead>
    </table>
  </div>
</template>

 <script>
export default {
  data() {
    return {
    }
  },
  methods: {
  },
  mounted() {
    $("#gateways").DataTable({
      ajax: "http://localhost:8080/api/restgateways",
      serverSide: true,
      columns: [
        {
          data: "id"
        },
        {
          data: "mac"
        }
      ]
    });
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