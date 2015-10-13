[1,2,3,4,5,6].each {
    job ("blabla${it}") {
        label 'blabla'
    }
}