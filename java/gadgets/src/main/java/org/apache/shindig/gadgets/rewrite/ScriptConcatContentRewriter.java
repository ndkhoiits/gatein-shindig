/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.shindig.gadgets.rewrite;

import com.google.inject.Inject;

import org.apache.shindig.common.uri.Uri;
import org.apache.shindig.gadgets.Gadget;
import org.apache.shindig.gadgets.rewrite.DomWalker;
import org.apache.shindig.gadgets.rewrite.DomWalker.Visitor;
import org.apache.shindig.gadgets.uri.ConcatUriManager;

import java.util.Arrays;
import java.util.List;

/**
 * REwrites scripts.
 *
 * @since 2.0.0
 */
public class ScriptConcatContentRewriter extends DomWalker.Rewriter {
  private final ContentRewriterFeature.Factory featureConfigFactory;
  private final ConcatUriManager concatUriManager;
  
  @Inject
  public ScriptConcatContentRewriter(ContentRewriterFeature.Factory featureConfigFactory,
      ConcatUriManager concatUriManager) {
    this.featureConfigFactory = featureConfigFactory;
    this.concatUriManager = concatUriManager;
  }
  
  @Override
  protected List<Visitor> makeVisitors(Gadget context, Uri gadgetUri) {
    ContentRewriterFeature.Config config = featureConfigFactory.get(gadgetUri);
    return Arrays.<Visitor>asList(new ConcatVisitor.Js(config, concatUriManager));
  }
}
