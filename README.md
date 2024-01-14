# OdeToCarStar
OdeToCarStar is an Android application that simply provides vehicle information on cars sold in the U.S.  However, the data merely provides vehicle information between the years 2015 and 2020.

<img src="https://github.com/xaerofox/OdeToCarStar/assets/12260970/18f0e68f-3849-446c-8bdc-df432a9e9c83" width=25% height=25%>
<img src="https://github.com/xaerofox/OdeToCarStar/assets/12260970/080b427c-a092-4690-890e-ab62b83cba9c" width=25% height=25%>
<img src="https://github.com/xaerofox/OdeToCarStar/assets/12260970/6c901a51-a13a-424b-b3ab-9ef61fd55555" width=25% height=25%>
<img src="https://github.com/xaerofox/OdeToCarStar/assets/12260970/2db5c70c-a21b-4f87-b6ca-941831f3c16a" width=25% height=25%>

## Getting started
The [Cars API](https://rapidapi.com/carapi/api/car-api2/) was used to get the vehicle information which utilizes the free tier of the API. So, acquire an API key and add `API_KEY=[your key]` to your `local.properties`. 

## Next steps
The project started originally for me explore the new Jetpack Compose UI toolkit, but there are some features and improvements I can add to this project.  Some simple and some ambitious:

* Caching - Probably an obvious one to reduce the reliance on making calls as the project uses a free tier which (if I recall) has a max of 5,000 requests per month.  I didn't quite add it early because of the had thoughts about the following stuff

* Kotlin Multiplatform - Getting the app to run iOS would probably be easy, but I'm aware that some libraries I might want to utilize may not be prepare for KMP.

* OBD (On-Board Diagnostics) - This one may be too ambitious as this will have me explore bluetooth development and possibly obtaining knowledge around the OBD II standards.  There may be libraries already, but will it also play nice with KMP?

* Gemni Pro?
