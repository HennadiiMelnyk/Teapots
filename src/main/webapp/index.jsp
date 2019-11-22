<%@ include file="/jspFragments/head.jspf" %>
<%@ include file="/jspFragments/header.jspf" %>

<div class="bg-light py-3">
      <div class="container">
        <div class="row">
          <div class="col-md-12 mb-0"><a href="index.jsp"><fmt:message key="home" bundle="${lang}"/></a> <span class="mx-2 mb-0">/</span> <strong
              class="text-black"><fmt:message key="tastes" bundle="${lang}"/></strong></div>
        </div>
      </div>
    </div>
<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img class="d-block w-100" src="/images/carousel-3.jpg" alt="First slide">
        </div>
        <div class="carousel-item">
            <img class="d-block w-100" src="/images/carousel-4.jpg" alt="Second slide">
        </div>
        <div class="carousel-item">
            <img class="d-block w-100" src="/images/teapot_background2.jpg" alt="Third slide">
        </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
<!-- Carousel
    ================================================== -->
    <div class="bg-light py-3">
          <div class="container">
            <div class="row">
              <div class="col-md-12 mb-0"><a href="index.jsp"><fmt:message key="home" bundle="${lang}"/></a> <span class="mx-2 mb-0">/</span> <strong
                  class="text-black"><fmt:message key="map" bundle="${lang}"/></strong></div>
            </div>
          </div>
        </div>
<div class="contacts-block">
    <iframe
        src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2482.601766847607!2d-0.15894818422955612!3d51.52052167963716!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48761ace9a2e67d7:0xd458de8d0fdc498e!2zQmFrZXIgU3QsIE1hcnlsZWJvbmUsIExvbmRvbiwg0JLQtdC70LjQutC-0LHRgNC40YLQsNC90ZbRjw!5e0!3m2!1suk!2sua!4v1561557035565!5m2!1suk!2sua"
        width="100%" height="600" frameborder="0" style="border:0" allowfullscreen></iframe>
</div>
<%@ include file="/jspFragments/footer.jspf" %>