package com.jtor.odetocarstar.data.model

object FakeTestData {

    fun getMake(): Make {
        return Make(id = 1, name = "Ford")
    }

    fun getCarsModel(): CarModel {
        return CarModel(id = 1, makeId = 1, name = "F-150")
    }

    fun getCarsModels(): List<CarModel> {
        return listOf(
            CarModel(id = 1, makeId = 1, name = "F-150"),
            CarModel(id = 2, makeId = 1, name = "Mustang"),
            CarModel(id = 3, makeId = 2, name = "Explorer")
        )
    }

    fun getCarsTrims(): List<CarTrim> {
        return listOf(
            CarTrim(
                created = "2021-01-15",
                description = "Regular Pickup",
                id = 194,
                invoice = 1,
                modelId = 1,
                modified = "2021-01-15",
                msrp = 45000,
                name = "Regular Pickup",
                year = 2021
            ),
            CarTrim(
                created = "2021-01-16",
                description = "XL",
                id = 230,
                invoice = 2,
                modelId = 2,
                modified = "2021-01-16",
                msrp = 33000,
                name = "XL",
                year = 2021
            ),
            CarTrim(
                created = "2021-01-17",
                description = "XL 4WD",
                id = 248,
                invoice = 3,
                modelId = 3,
                modified = "2021-01-17",
                msrp = 48000,
                name = "XL 4WD",
                year = 2021
            )
        )
    }

    fun getCarsTrimsId(): Int {
        return 1
    }

    fun getMakeModel(): MakeModel {
        return MakeModel(
            id = 1,
            make = Make(id = 1, name = "Ford"),
            makeId = 1,
            name = "F-150"
        )
    }

    fun getMakeModels(): List<MakeModel> {
        return listOf(
            MakeModel(
                id = 1,
                make = Make(id = 1, name = "Ford"),
                makeId = 1,
                name = "F-150"
            ),
            MakeModel(
                id = 2,
                make = Make(id = 1, name = "Ford"),
                makeId = 1,
                name = "Mustang"
            ),
            MakeModel(
                id = 3,
                make = Make(id = 2, name = "Chevy"),
                makeId = 2,
                name = "Suburban"
            )
        )
    }

    fun getCollection(): Collection {
        return Collection(
            count = 2500,
            first = "/makes?limit=10",
            last = "/makes?page=10&limit=10",
            next = "/makes?page=2&limit=10",
            pages = 250,
            prev = "/makes?page=0&limit=10",
            total = 2500,
            url = "/makes?page=1&limit=10"
        )
    }

    fun getTrimMileage(): TrimMileage {
        return TrimMileage(
            batteryCapacityElectric = null,
            combineMpg = 19,
            cityMpg = 17,
            cityMpgElectric = null,
            combinedMpgElectric = null,
            highwayMpg = 24,
            highwayMpgElectric = null,
            kwh100MiElectric = null,
            timeToCharge = null,
            fuelTankCapacity = "26.5",
            id = 1,
            rangeCity = 461,
            rangeElectric = null,
            rangeHighway = 638,
            trimId = 1
        )
    }

    fun getTrimEngine(): TrimEngine {
        return TrimEngine(
            camType = "DOHC",
            cylinders = "3",
            driveType = "RWD",
            engineType = "V8",
            fuelType = "Regular Unleaded F 87",
            horsepowerHp = 325,
            horsepowerRpm = 5500,
            id = 1,
            size = "5.0L V8",
            torquePoundFoot = 355,
            torqueRpm = 4250,
            transmission = "Automatic 10-Speed",
            valveTiming = null,
            valves = 32,
            trimId = 1
        )
    }

    fun getTrimBody(): TrimBody {
        return TrimBody(
            cargoCapacity = "52.8",
            curbWeight = 5500,
            doors = 4,
            frontTrack = "69.7",
            grossWeight = "7050",
            groundClearance = "8.4",
            height = "77.7",
            id = 1,
            length = "231.9",
            maxCargoCapacity = "52.8",
            maxPayload = null,
            maxTowingCapacity = "13200",
            rearTrack = "69.1",
            seats = 5,
            type = "SUV",
            wheelBase = "145",
            width = "79.9",
            trimId = 1
        )
    }

    fun getTrimColorsType(): List<TrimColor> {
        return listOf(
            TrimColor(id = 1, trimId = 1, name = "Oxford White", rgb = "255,255,255"),
            TrimColor(id = 2, trimId = 1, name = "Agate Grey Metallic", rgb = "192,192,192"),
            TrimColor(id = 3, trimId = 1, name = "Ingot Silver", rgb = "160,160,160")
        )
    }

    fun getTrimInteriorColorsType(): List<TrimColor> {
        return listOf(
            TrimColor(id = 4, trimId = 1, name = "Media Gray", rgb = "100,100,100"),
            TrimColor(id = 5, trimId = 1, name = "Black", rgb = "0,0,0"),
            TrimColor(id = 6, trimId = 1, name = "Dark Titanium", rgb = "50,50,50")
        )
    }

    fun getCarTrimDetail(): CarTrimDetail {
        return CarTrimDetail(
            created = "2021-01-15T00:00:00.000Z",
            description = "F-150 XLT SuperCab 4WD",
            id = 1,
            invoice = 32000,
            makeModel = getMakeModel(),
            modelId = 1,
            trimBody = getTrimBody(),
            trimEngine = getTrimEngine(),
            trimExteriorColors = getTrimColorsType(),
            trimInteriorColors = getTrimInteriorColorsType(),
            trimMileage = getTrimMileage(),
            modified = "2021-01-15T00:00:00.000Z",
            msrp = 38000,
            name = "XLT SuperCab 4WD",
            year = 2021
        )
    }

    fun getCarTrimDetails(): List<CarTrimDetail> {
        return listOf(
            getCarTrimDetail(),
            CarTrimDetail(
                created = "2021-01-16T00:00:00.000Z",
                description = "F-150 Lariat SuperCab 4WD",
                id = 2,
                invoice = 41000,
                makeModel = MakeModel(
                    id = 2,
                    make = Make(id = 1, name = "Ford"),
                    makeId = 1,
                    name = "Mustang"
                ),
                modelId = 2,
                trimBody = TrimBody(
                    cargoCapacity = "44.5",
                    curbWeight = 5000,
                    doors = 2,
                    frontTrack = "66.7",
                    grossWeight = "6900",
                    groundClearance = "5.5",
                    height = "75.4",
                    id = 2,
                    length = "188.3",
                    maxCargoCapacity = "44.5",
                    maxPayload = null,
                    maxTowingCapacity = "13200",
                    rearTrack = "63.0",
                    seats = 4,
                    type = "Coupe",
                    wheelBase = "107.1",
                    width = "75.4",
                    trimId = 2
                ),
                trimEngine = TrimEngine(
                    camType = "DOHC",
                    cylinders = "6",
                    driveType = "RWD",
                    engineType = "V8",
                    fuelType = "Premium Unleaded",
                    horsepowerHp = 480,
                    horsepowerRpm = 6000,
                    id = 2,
                    size = "5.0L V8",
                    torquePoundFoot = 415,
                    torqueRpm = 4600,
                    transmission = "Automatic 10-Speed",
                    valveTiming = "Variable",
                    valves = 32,
                    trimId = 2
                ),
                trimExteriorColors = listOf(
                    TrimColor(id = 1, trimId = 2, name = "Shadow Black", rgb = "0,0,0"),
                    TrimColor(id = 2, trimId = 2, name = "Race Red", rgb = "255,0,0")
                ),
                trimInteriorColors = listOf(
                    TrimColor(id = 1, trimId = 2, name = "Ebony", rgb = "0,0,0"),
                    TrimColor(id = 2, trimId = 2, name = "Medium Light Stone", rgb = "210,180,150")
                ),
                trimMileage = TrimMileage(
                    batteryCapacityElectric = null,
                    combineMpg = 18,
                    cityMpg = 15,
                    cityMpgElectric = null,
                    combinedMpgElectric = null,
                    highwayMpg = 22,
                    highwayMpgElectric = null,
                    kwh100MiElectric = null,
                    timeToCharge = null,
                    fuelTankCapacity = "16.0",
                    id = 2,
                    rangeCity = 240,
                    rangeElectric = null,
                    rangeHighway = 352,
                    trimId = 2
                ),
                modified = "2021-01-16T00:00:00.000Z",
                msrp = 45000,
                name = "Lariat SuperCab 4WD",
                year = 2021
            )
        )
    }
}
