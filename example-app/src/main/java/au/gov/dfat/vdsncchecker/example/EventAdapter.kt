//  Copyright (c) 2021, Commonwealth of Australia. vds.support@dfat.gov.au
//
//  Licensed under the Apache License, Version 2.0 (the "License"); you may not
//  use this file except in compliance with the License. You may obtain a copy
//  of the License at:
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
//  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
//  License for the specific language governing permissions and limitations
//  under the License.

package au.gov.dfat.vdsncchecker.example

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.gov.dfat.lib.vdsncchecker.VDSVe

class EventAdapter(private val events: List<VDSVe>)
    : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.vaccination_event_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = events[position]
        holder.apply {
            vaccine.text = item.des
            brand.text = item.nam
            diseaseAgent.text = item.dis
        }

        // Bind list of details
        holder.details.apply {
            layoutManager = object : LinearLayoutManager(holder.details.context, VERTICAL, false) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }.apply {
                initialPrefetchItemCount = item.vd.size
            }
            adapter = DetailAdapter(item.vd)
            setRecycledViewPool(RecyclerView.RecycledViewPool())
        }
    }

    override fun getItemCount(): Int = events.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val vaccine: TextView = view.findViewById(R.id.valueVaccine)
        val brand: TextView = view.findViewById(R.id.valueBrand)
        val diseaseAgent: TextView = view.findViewById(R.id.valueDiseaseAgent)
        val details: RecyclerView = view.findViewById(R.id.details)
    }
}