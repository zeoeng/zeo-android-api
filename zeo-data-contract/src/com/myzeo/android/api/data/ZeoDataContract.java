/* @(#)ZeoDataContract.java
 *
 *========================================================================
 * Copyright 2011 by Zeo Inc. All Rights Reserved
 *========================================================================
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Date: $Date$
 * Author: Brandon Edens <brandon.edens@myzeo.com>
 * Version: $Revision$
 */

package com.myzeo.android.api.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Contract that lays out what fields are available to others when accessing Zeo data from the Zeo
 * Provider.
 *
 * <h1>Description</h1>
 *
 * <p>
 *
 * This contract specifies what data is made publically available by Zeo for usage with third party
 * apps. The contract specified here is guaranteed to remain operable with the Zeo app. Please see
 * the document TODO point to web link for further information on how to use this contract to access
 * Zeo data from within your application.
 *
 * </p>
 *
 * <p>
 *
 * Please note that this contract refers to sleep episode in preference to sleep events though the
 * actual text string is "sleep_event". This is due to the application originally using the name
 * event to represent some time the user slept. Recently Zeo has decided that the better term for
 * this is episode and eventually the app's internal systems will be updated to reflect this in a
 * manner that is transparent to this contract.
 *
 * </p>
 *
 * @author Brandon Edens
 * @version $Revision$
 */
public class ZeoDataContract {

    /** The major version for the contract; non-backwards compatible version changes
     * @see <a href="http://semver.org/">Semantic Versioning</a>
     */
    public static final int VERSION_MAJOR = 1;

    /** The minor version for the contract; increments denote backwards compatible changes.
     * @see <a href="http://semver.org/">Semantic Versioning</a>
     */
    public static final int VERSION_MINOR = 0;

    /** The patch of the contract; indicates bug fixes.
     * @see <a href="http://semver.org/">Semantic Versioning</a>
     */
    public static final int VERSION_PATCH = 0;

    /**
     * The version of the Zeo data contract.
     * @see <a href="http://semver.org/">Semantic Versioning</a>
     */
    public static final String VERSION =
        "" + VERSION_MAJOR + VERSION_MINOR + VERSION_PATCH;

    /** The content authority is Zeo. */
    public static final String CONTENT_AUTHORITY = "com.myzeo";

    /** URI base of all provider queries. */
    public static final Uri BASE_CONTENT_URI =
        Uri.parse("content://" + CONTENT_AUTHORITY);

    interface AlarmAlertEventColumns {
        /** Foreign key pointing to the sleep episode for which this alert correspond to. */
        String SLEEP_EPISODE_ID = "sleep_event_id";

        /** The reason the alarm rang which is used in instances of smart wake to allow user to know
         * why the Headband thought it was correct time to wake user.
         * @see AlarmAlertEvent#ALARM_REASON_NONE
         * @see AlarmAlertEvent#ALARM_REASON_DEEP_RISING
         * @see AlarmAlertEvent#ALARM_REASON_NREM_TO_REM_TRANSITION
         * @see AlarmAlertEvent#ALARM_REASON_REM_TO_NREM_TRANSITION
         * @see AlarmAlertEvent#ALARM_REASON_WAKE_ON_WAKE
         */
        String REASON = "alarm_ring_reason";

        /** Boolean that indicates whether or not Zeo smart wake was engaged. */
        String SMART_WAKE = "smart_wake";

        /** Unix timestamp in milliseconds marking when the alarm went off. */
        String TIMESTAMP = "alarm_timestamp";

        /** A string that indicates what wake music was used to awake the user. This can be either
         * Zeo music or Android ringtones. A URI with content authority, content://com.myzeo.music
         * represents Zeo music, media:// represents Android ringtone.
         */
        String WAKE_TONE = "wake_tone";

        /** The number of minutes prior to the alarm time that the user configured the alarm to
         * possibly awake them when smart wake is enabled.
         */
        String WAKE_WINDOW = "wake_window";
    }

    interface AlarmSnoozeEventColumns {
        /** Foreign key pointing to the sleep episode for which this snooze correspond to. */
        String SLEEP_EPISODE_ID = "sleep_event_id";

        /** Number of milliseconds alarm snoozed. */
        String DURATION = "alarm_snooze_duration";

        /** Unix timestamp in milliseconds that marks the time that the user snoozed the alarm. */
        String TIMESTAMP = "alarm_snooze_timestamp";
    }

    interface AlarmTimeoutEventColumns {
        /** Foreign key pointing to the sleep episode for which this timeout correspond to. */
        String SLEEP_EPISODE_ID = "sleep_event_id";

        /** Number of milliseconds before alarm timed out. */
        String DURATION = "alarm_timeout_duration";

        /** Unix timestamp in milliseconds that marks the time that the alarm automatically silenced
         * itself.
         */
        String TIMESTAMP = "alarm_timeout_timestamp";
    }

    interface HeadbandColumns {

        /** The current mode of the sleep algorithm running on headband.
         * @see Headband#ALGO_MODE_UNDEFINED
         * @see Headband#ALGO_MODE_IDLE
         * @see Headband#ALGO_MODE_TENTATIVE_ACTIVE
         * @see Headband#ALGO_MODE_ACTIVE
         * @see Headband#ALGO_MODE_TENTATIVE_IDLE
         */
        String ALGORITHM_MODE = "algorithm_mode";

        /**
         * String representation of bluetooth 42 bit address in the form of: 11:22:33:44:55:66 which
         * is natural for constructing Android's BluetoothDevice.
         */
        String BLUETOOTH_ADDRESS = "bluetooth_address";

        /**
         * Bluetooth name shown to the user.
         */
        String BLUETOOTH_FRIENDLY_NAME = "bluetooth_friendly_name";

        /**
         * Flag indicating whether or not headband is paired to Android device. True if paired;
         * false otherwise.
         */
        String BONDED = "bonded";

        /**
         * Number of milliseconds offset between Android device's notion of time versus the
         * headband's.
         */
        String CLOCK_OFFSET = "clock_offset";

        /**
         * Whether or not the headband is currently connected to the Android device.
         */
        String CONNECTED = "connected";

        /**
         * Flag that indicates headband is docked with docking station. True if docked; false
         * otherwise.
         */
        String DOCKED = "docked";

        /**
         * Flag indicating that headband is currently on user's head. True if on head; false
         * otherwise.
         */
        String ON_HEAD = "on_head";

        /** The software version for firmware running on Zeo headband. */
        String SW_VERSION ="software_version";
    }

    interface SleepEpisodeColumns {
        /** The TZ string representing timezone that this sleep event was created in.
         * @see java.util.Timezone
         */
        String TIMEZONE = "timezone";

        /**
         * The starting timestamp (Unix timestamp in milliseconds) for the sleep event which is
         * earliest point in time where sleep was registered via Headband.
         */
        String START_TIMESTAMP = "start_timestamp";

        /**
         * The ending timestamp for the sleep event which is latest point in time where sleep was
         * not registered.
         */
        String END_TIMESTAMP = "end_timestamp";
    }

    interface SleepRecordColumns {
        /** Foreign key pointing to the sleep episode for which this sleep record corresponds to. */
        String SLEEP_EPISODE_ID = "sleep_event_id";

        /**
         * Number of times user awoke throughout the night of sleep.
         */
        String AWAKENINGS = "awakenings";

        /**
         * Binary blob containing an array of bytes where each value in array represents user's
         * sleep stage after 30 seconds of recording by Zeo's headband.
         * @see SleepRecord#SLEEP_STAGE_UNDEFINED
         * @see SleepRecord#SLEEP_STAGE_WAKE
         * @see SleepRecord#SLEEP_STAGE_LIGHT
         * @see SleepRecord#SLEEP_STAGE_DEEP
         * @see SleepRecord#SLEEP_STAGE_NMAX
         */
        String BASE_HYPNOGRAM = "base_hypnogram";

        /**
         * Number of elements contained within the base hypnogram where each element is recording of
         * 30 seconds of sleep.
         */
        String BASE_HYPNOGRAM_COUNT = "base_hypnogram_count";

        /**
         * Binary blob containing an array of bytes where each value in array represents the 5
         * minute majority sleep stage user was in derived from the base hypnogram.
         * @see SleepRecord#SLEEP_STAGE_UNDEFINED
         * @see SleepRecord#SLEEP_STAGE_WAKE
         * @see SleepRecord#SLEEP_STAGE_LIGHT
         * @see SleepRecord#SLEEP_STAGE_DEEP
         * @see SleepRecord#SLEEP_STAGE_NMAX
         */
        String DISPLAY_HYPNOGRAM = "display_hypnogram";

        /**
         * Number of elements contained within the display hypnogram where each element is 5 minutes
         * of sleep.
         */
        String DISPLAY_HYPNOGRAM_COUNT = "display_hypnogram_count";

        /**
         * Unix timestamp in milliseconds that represents the conclusion of a sleep record as
         * recorded by Zeo headband.
         */
        String END_OF_NIGHT = "end_of_night";

        /**
         * The reason that this sleep record concluded. This field is updated as headband conditions
         * change and allows the software to determine the ultimate fate of the recording of sleep's
         * conclusion.
         * @see SleepRecord#END_REASON_COMPLETE
         * @see SleepRecord#END_REASON_ACTIVE
         * @see SleepRecord#END_REASON_BATTERY_DIED
         * @see SleepRecord#END_REASON_DISCONNECTED
         * @see SleepRecord#END_REASON_SERVICE_KILLED
         */
        String END_REASON = "end_reason";

        /** Foreign key pointing to the headband that recorded this sleep record. */
        String HEADBAND_ID = "headband";

        /** A localized form of the start of night timestamp (used for synchronization purposes). */
        String LOCALIZED_START_OF_NIGHT = "localized_start_of_night";

        /**
         * The originating source for this record which indicates where this sleep record was
         * gathered from.
         * @see SleepRecord#DATA_SOURCE_PRIMARY
         * @see SleepRecord#DATA_SOURCE_REMOTE
         */
        String SOURCE = "source";

        /**
         * Unix timestamp in milliseconds that represents the beginning of the recorded sleep data.
         */
        String START_OF_NIGHT = "start_of_night";

        /**
         * TZ string holding timezone that phone was in last time sleep record was received while
         * algorithm was still active.
         * @see java.util.Timezone
         */
        String TIMEZONE = "timezone";

        /** Number of 30 second sleep epochs that user was in deep sleep. */
        String TIME_IN_DEEP = "time_in_deep";

        /** Number of 30 second sleep epochs that user was in light sleep. */
        String TIME_IN_LIGHT = "time_in_light";

        /** Number of 30 second sleep epochs that user was in REM sleep. */
        String TIME_IN_REM = "time_in_rem";

        /** Number of 30 second epochs that user was awake. */
        String TIME_IN_WAKE = "time_in_wake";

        /** Number of 30 second epochs before sleep onset (user was asleep) occurred. */
        String TIME_TO_Z = "time_to_z";

        /**
         * Total amount of time the user was asleep; from the first epoch of sleep to last epoch of
         * sleep.
         */
        String TOTAL_Z = "total_z";

        /**
         * A metric by which Zeo determines how well the user slept.
         */
        String ZQ_SCORE = "zq_score";
    }

    /**
     * Generic columns that specify created/updated timestamps for usage in
     * other tables.
     */
    public interface TimestampColumns {
        /** Unix timestamp marking when this row was created. */
        String CREATED_ON = "created_on";

        /** Unix timestamp that marks last time this row was updated. */
        String UPDATED_ON = "updated_on";
    }

    public static class AlarmAlertEvent
        implements AlarmAlertEventColumns, BaseColumns, TimestampColumns {

        /** The provider path to alarm alert information. */
        private static final String PATH = "alarm_events";

        /** The URI used to access alarm alert events. */
        public static final Uri CONTENT_URI =
            BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();

        /** No reason for ring (alarm rang at time specified by user) */
        public static final int ALARM_REASON_NONE = 0;
        /** Sleep algorithm detected user rising out of deep sleep */
        public static final int ALARM_REASON_DEEP_RISING = 1;
        /** User went from non-REM to REM sleep */
        public static final int ALARM_REASON_NREM_TO_REM_TRANSITION = 2;
        /** User went from rem to non-rem sleep */
        public static final int ALARM_REASON_REM_TO_NREM_TRANSITION = 3;
        /** User was already awake so the alarm rang. */
        public static final int ALARM_REASON_WAKE_ON_WAKE = 4;
    }

    public static class AlarmSnoozeEvent
        implements AlarmSnoozeEventColumns, BaseColumns, TimestampColumns {

        /** Provider path to alarm snooze information. */
        private static final String PATH = "alarm_snooze_events";

        /** The URI used to access alarm snooze events. */
        public static final Uri CONTENT_URI =
            BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();
    }

    public static class AlarmTimeoutEvent
        implements AlarmTimeoutEventColumns, BaseColumns, TimestampColumns {

        /** Provider path to alarm timeout events. */
        private static final String PATH = "alarm_timeout_events";

        /** The URI used to access alarm timeout events. */
        public static final Uri CONTENT_URI =
            BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();
    }

    /**
     * A sleep event or sleep episode is a meta joining table that unites various sources of sleep
     * information into a coherent logical collection of data for a given event/episode of
     * sleep. The mapping of real life sleep to Zeo's notion of a sleep episode/event is one to one.
     */
    public static class SleepEpisode
        implements BaseColumns, SleepEpisodeColumns, TimestampColumns {

        /** The provider path to the sleep events. */
        private static final String PATH = "sleep_events";

        /** The URI used to access sleep episode information. */
        public static final Uri CONTENT_URI =
            BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();
    }

    /**
     * Representation of the state of the Zeo headband which is gathered form the communication
     * messaging system that occurs between the Android device and the headband.
     */
    public static class Headband
        implements BaseColumns, HeadbandColumns, TimestampColumns {

        /** The provider path to the headbands. */
        private static final String PATH = "headbands";

        /** URI used to access headband information. */
        public static final Uri CONTENT_URI =
            BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();

        /** Algorithm in an undefined state (not known) */
        public static final int ALGO_MODE_UNDEFINED = -1;
        /** Sleep algorithm is idle. */
        public static final int ALGO_MODE_IDLE = 0;
        /** Sleep algorithm is tentative active (starting up) */
        public static final int ALGO_MODE_TENTATIVE_ACTIVE = 1;
        /** Sleep algorithm is actively recording sleep. */
        public static final int ALGO_MODE_ACTIVE = 2;
        /** Sleep algorithm is tentative idle (shutting down) */
        public static final int ALGO_MODE_TENTATIVE_IDLE = 3;
    }

    /**
     * Data records sent by a Zeo headband or culled from the myzeo.com website. Regardless of the
     * source of these records; this data represents information that Zeo recorded and processed
     * from user's brain activity.
     */
    public static class SleepRecord
        implements BaseColumns, SleepRecordColumns, TimestampColumns {

        /** Path to sleep record information in the provider. */
        private static final String PATH = "sleep_records";

        /** The URI used to access sleep record data. */
        public static final Uri CONTENT_URI =
            BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();

        /** Zeo recording of sleep record concluded normally. */
        public static final int END_REASON_COMPLETE = 0;
        /** Headband is currently still actively recording sleep. */
        public static final int END_REASON_ACTIVE = 1;
        /** The battery died in the headband which stopped recording. */
        public static final int END_REASON_BATTERY_DIED = 2;
        /** Headband was disconnected from the Android device. */
        public static final int END_REASON_DISCONNECTED = 3;
        /** The Android service that interfaces with headband was killed by Android. */
        public static final int END_REASON_SERVICE_KILLED = 4;

        /** Sleep stage is unknown. */
        public static final byte SLEEP_STAGE_UNDEFINED = 0;
        /** User is awake. */
        public static final byte SLEEP_STAGE_WAKE = 1;
        /** User is in REM sleep. */
        public static final byte SLEEP_STAGE_REM = 2;
        /** User is in light sleep. */
        public static final byte SLEEP_STAGE_LIGHT = 3;
        /** User is in deep sleep. */
        public static final byte SLEEP_STAGE_DEEP = 4;
        /** Number of sleep stages defined. */
        public static final int SLEEP_STAGE_NMAX = 5;

        /** Data source for sleep record was the headband. */
        public static final int DATA_SOURCE_PRIMARY = 0;
        /** Data source for sleep record was myzeo.com */
        public static final int DATA_SOURCE_REMOTE = 1;
    }

    /**
     * Do not instantiate objects of type ZeoDataContract.
     */
    private ZeoDataContract() {
    }
}

