package com.example.ynov_lyon_bde.domain.viewmodel.event

import android.util.EventLog
import com.example.ynov_lyon_bde.data.model.Event
import com.example.ynov_lyon_bde.data.model.EventType
import com.example.ynov_lyon_bde.data.model.ImageType

class EventViewModel {

    var eventList = listOf(
        Event(id = "1", name = "Espit Chupitos", date = "O3/11/2020", type = EventType.KOLOK, image = ImageType.KOLOK, address = "115 avenue Du test", description = "C'est une sacré descriptionC'est une sacré descriptionC'est une sacré descriptionC'est une sacré descriptionC'est une sacré descriptionC'est une sacré descriptionC'est une sacré descriptionC'est une sacré descriptionC'est une sacré description", price = 5),
        Event(id = "1", name = "Espit Chupitos", date = "O3/11/2020", type = EventType.KOLOK, image = ImageType.KOLOK, address = "115 avenue Du test", description = "C'est une sacré descriptionC'est une sacré descriptionC'est une sacré descriptionC'est une sacré descriptionC'est une sacré descriptionC'est une sacré descriptionC'est une sacré descriptionC'est une sacré descriptionC'est une sacré description", price = 5),
        Event(id = "1", name = "Espit Chupitos", date = "O3/11/2020", type = EventType.KOLOK, image = ImageType.KOLOK, address = "115 avenue Du test", description = "C'est une sacré descriptionC'est une sacré descriptionC'est une sacré descriptionC'est une sacré descriptionC'est une sacré descriptionC'est une sacré descriptionC'est une sacré descriptionC'est une sacré descriptionC'est une sacré description", price = 5),
    )
}
