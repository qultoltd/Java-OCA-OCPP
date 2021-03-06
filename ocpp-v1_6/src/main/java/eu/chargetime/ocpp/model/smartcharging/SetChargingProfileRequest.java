package eu.chargetime.ocpp.model.smartcharging;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.core.ChargingProfile;
import eu.chargetime.ocpp.model.core.ChargingProfilePurposeType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * ChargeTime.eu - Java-OCA-OCPP
 *
 * MIT License
 *
 * Copyright (C) 2017 Emil Christopher Solli Melar <emil@iconsultable.no>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

@XmlRootElement
public class SetChargingProfileRequest implements Request {
    private Integer connectorId;
    private ChargingProfile chargingProfile;

    public SetChargingProfileRequest() { }

    public SetChargingProfileRequest(Integer connectorId, ChargingProfile chargingProfile) {
        this.connectorId = connectorId;
        this.chargingProfile = chargingProfile;
    }

    /**
     * This identifies which connector of the Charge Point is used.
     *
     * @return connector.
     */
    public Integer getConnectorId() {
        return connectorId;
    }

    /**
     * Required. This identifies which connector of the Charge Point is used.
     *
     * @param connectorId integer. value &gt; 0
     * @throws PropertyConstraintException Value was 0 or negative.
     */
    @XmlElement
    public void setConnectorId(Integer connectorId) throws PropertyConstraintException {
        if (connectorId <= 0)
            throw new PropertyConstraintException("connectorId", connectorId);

        this.connectorId = connectorId;
    }

    /**
     * Charging Profile to be used by the Charge Point for the requested transaction.
     *
     * @return the {@link ChargingProfile}.
     */
    public ChargingProfile getChargingProfile() {
        return chargingProfile;
    }

    /**
     * Optional. Charging Profile to be used by the Charge Point for the requested transaction.
     * {@link ChargingProfile#setChargingProfilePurpose(ChargingProfilePurposeType)} MUST be set to TxProfile.
     *
     * @param chargingProfile   the {@link ChargingProfile}.
     */
    @XmlElement(name = "csChargingProfiles")
    public void setChargingProfile(ChargingProfile chargingProfile) {
        this.chargingProfile = chargingProfile;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean validate() {
        boolean valid = true;
        valid &= connectorId != null && connectorId > 0;

        if (chargingProfile != null) {
            valid &= chargingProfile.validate();
        }
        return valid;
    }
}
