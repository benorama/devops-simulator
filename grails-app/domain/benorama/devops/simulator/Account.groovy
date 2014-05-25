package benorama.devops.simulator

class Account {

    static transients = [
            'fullName',
            'trackerId'
    ]

    Date dateCreated
    long facebookId = 0
    String firstName = ''
    String lastName = ''
    Date lastUpdated
    String name = ''

    static constraints = {
        facebookId notEqual: 0L, unique: true
        firstName maxSize: 255
        lastName maxSize: 255
        name maxSize: 255
    }

    static mapping = {
        columns {
            // Indexes
            dateCreated index: 'ix_user_date_created'
            facebookId index: 'ix_user_facebook_id', unique: true
        }
    }

    String getFullName(int maxChars = 0) {
        String fullName = name
        if(firstName && lastName) {
            fullName = "${firstName} ${lastName}"
        }
        if (maxChars && fullName.length() > maxChars){
            fullName = "${fullName[0..maxChars]}..."
        }
        return fullName
    }

    String getTrackerId() {
        // Get unique ID (for Segment.io identify and event tracking)
        "${facebookId}".encodeAsMD5()
    }

    boolean equals(other) {
        if(other?.is(this))return true
        if(!(other instanceof Account)) return false
        if(!id || !other?.id || id != other?.id) return false
        return true
    }

    String toString() {
        "Account(id:$id, facebookId:$facebookId, fullName:'$fullName')"
    }
}
